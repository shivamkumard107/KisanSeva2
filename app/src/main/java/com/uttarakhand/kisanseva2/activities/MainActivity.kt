package com.uttarakhand.kisanseva2.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.fragments.HomeFragment
import com.uttarakhand.kisanseva2.fragments.InformationFragment
import com.uttarakhand.kisanseva2.fragments.ProfileFragment
import com.uttarakhand.kisanseva2.fragments.QualityTesting

class MainActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private val mDatabase: DatabaseReference? = null
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = HomeFragment()
            R.id.navigation_info -> selectedFragment = InformationFragment()
            R.id.navigation_quality -> selectedFragment = QualityTesting()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth!!.currentUser == null) {
            Toast.makeText(this, "Unauthorized!!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    HomeFragment()).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_signout) {
            firebaseAuth!!.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else if (item.itemId == R.id.action_weather) {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}