package com.example.githubusers

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var hintMessage: TextView
    // Listener created to change the Search Bar HINT as user swipe between both menu options
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.tab_users -> {
                hintMessage.setHint(R.string.hint_search_users)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_favorites -> {
                hintMessage.setHint(R.string.hint_search_favorites)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    // On Creation this will ensure Menu work and will get the Search Bar reference to change the hint as needed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        hintMessage = findViewById(R.id.search_bar)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
