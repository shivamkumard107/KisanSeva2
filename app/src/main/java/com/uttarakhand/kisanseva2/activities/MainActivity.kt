package com.uttarakhand.kisanseva2.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.inventoryManagement.OrdersInfoActivity
import com.uttarakhand.kisanseva2.fragments.*
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var firebaseAuth: FirebaseAuth? = null
    private val mDatabase: DatabaseReference? = null
    private var drawer: DrawerLayout? = null

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = HomeFragment()
            R.id.navigation_info -> selectedFragment = InformationFragment()
            R.id.navigation_quality -> selectedFragment = QualityTesting()
            R.id.navigation_chat -> selectedFragment = ChatFragment()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).addToBackStack("null").commit()
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


        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_draw_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            if (intent.extras != null) {
                when {
                    intent.extras!!.getString("open", "inventory") == "inventory" -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                HomeFragment()).addToBackStack("null").commit()
                    }
                    intent.extras!!.getString("open", "inventory") == "information" -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                InformationFragment()).addToBackStack("null").commit()
                    }
                    intent.extras!!.getString("open", "inventory") == "quality" -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                QualityTesting()).addToBackStack("null").commit()
                    }
                    intent.extras!!.getString("open", "inventory") == "chat" -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                ChatFragment()).addToBackStack("null").commit()
                    }
                    intent.extras!!.getString("open", "inventory") == "profile" -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                ProfileFragment()).addToBackStack("null").commit()
                    }
                }
            } else {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                        HomeFragment()).addToBackStack("null").commit()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                        SearchFragment()).addToBackStack("null").commit()
                return true
            }
            R.id.action_filter -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_insurance -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pmfby.gov.in/"))
                startActivity(browserIntent)
            }
            R.id.nav_lang -> changeLanguage()
            R.id.nav_orders -> startActivity(Intent(this, OrdersInfoActivity::class.java))
            R.id.nav_negotiate -> startActivity(Intent(this, NegotiatedActivity::class.java))
            R.id.nav_weather -> startActivity(Intent(this, WeatherActivity::class.java))
            R.id.nav_loan -> startActivity(Intent(this, BlockchainActivity::class.java))
            R.id.nav_feedback -> startActivity(Intent(this, FeedbackActivity::class.java))
            R.id.nav_signout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun changeLanguage() {
        val items = arrayOf("Hindi", "English")
        var alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Choose Language...")
        alertDialogBuilder.setSingleChoiceItems(items, -1,
                DialogInterface.OnClickListener { dialog, which ->
                    if (which == 0) {
                        setLocale("hi")
                        recreate()
                    } else if (which == 1) {
                        setLocale("en")
                        recreate()
                    }
                    dialog.dismiss()
                })
        alertDialogBuilder.create()
        alertDialogBuilder.show()

    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }

    override fun onBackPressed() {
        when {
            drawer!!.isDrawerOpen(GravityCompat.START) -> {
                drawer!!.closeDrawer(GravityCompat.START)
            }
            else -> {
                finish();
                moveTaskToBack(true);
                super.onBackPressed()
            }
        }
    }

}