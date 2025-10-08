package com.example.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.ui.adapters.CoursesAdapter
import com.example.presentation.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val vm: MainScreenViewModel by viewModels()

    private val adapter = CoursesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val initialLeft = view.paddingLeft
        val initialTop = view.paddingTop
        val initialRight = view.paddingRight
        val initialBottom = view.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = initialLeft,
                right = initialRight,
                top = initialTop + bars.top,
                bottom = initialBottom + bars.bottom
            )
            insets
        }
        _binding = FragmentMainBinding.bind(view)
        binding.recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.courses.collect { courses ->
                    adapter.items = courses.toList()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}