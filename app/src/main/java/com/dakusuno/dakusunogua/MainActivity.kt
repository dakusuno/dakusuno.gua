package com.dakusuno.dakusunogua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(this,R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController,appBarConfiguration)

        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_change_settings -> {
                    navController.navigate(R.id.settingFragment)

                    true
                }
                R.id.action_favourite -> {
                    navController.navigate(R.id.favouriteFragment)
                    true
                }
                else -> false
            }
        }

    }
}