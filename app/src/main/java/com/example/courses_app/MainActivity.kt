package com.example.courses_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.ui.navigation.OnAuthSuccessListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnAuthSuccessListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        val menu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            menu.isVisible = dest.id != R.id.authFragment
        }
        menu.setupWithNavController(navController)
    }

    override fun onAuthSuccess() {
        val options = navOptions {
            popUpTo(R.id.authFragment) {
                inclusive = true
            }
        }
        findNavController(R.id.nav_host_fragment).navigate(R.id.mainFragment, null, options)
    }
}