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
import com.example.domain.models.CourseModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFavouritesBinding
import com.example.presentation.ui.adapters.CoursesAdapter
import com.example.presentation.ui.data.toCourseItem
import com.example.presentation.ui.viewmodels.FavouritesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val vm: FavouritesScreenViewModel by viewModels()

    private val adapter = CoursesAdapter(
        onLikeClick = { id, isLike ->
            vm.deleteFromFavourites(id)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val initialLeft = view.paddingLeft
        val initialTop = view.paddingTop
        val initialRight = view.paddingRight

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = initialLeft,
                right = initialRight,
                top = initialTop + bars.top,
            )
            insets
        }
        _binding = FragmentFavouritesBinding.bind(view)
        binding.recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.recyclerView.clipToPadding = false

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.favourites.collect { courses: List<CourseModel> ->
                    adapter.items = courses.map { it.toCourseItem() }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}