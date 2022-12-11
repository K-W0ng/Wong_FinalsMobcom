package edu.mobcom.wong_finalsmobcom


import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.mobcom.wong_finalsmobcom.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvNameHome = findViewById<TextView>(R.id.tvNameHome)
        val rCE=intent!!.getStringExtra("Email").toString()
        val rec: String = rCE

        tvNameHome.setText(rec)

        binding.addEventHome.setOnClickListener {
            val i = Intent(this, EventScheduler::class.java)
            startActivity(i)
        }

        binding.viewProfileHome.setOnClickListener {
            val i = Intent(this, UserProfileActivity::class.java)
            startActivity(i)
        }

        binding.webHome.setOnClickListener {
            val strURL = "https://www.google.com"
            val iBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(strURL))
            startActivity(iBrowser)
        }

        binding.emailHome.setOnClickListener {
            val iSend = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
            }
            startActivity(iSend)
        }

        binding.dialHome.setOnClickListener {
            val iDialer = Intent(Intent.ACTION_DIAL)
            startActivity(iDialer)
        }

        binding.calculatorHome.setOnClickListener{
            val calculatorPackage = "com.android.calculator2"
            val calculatorClass = "com.android.calculator2.Calculator"
            val intent = Intent()
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.component = ComponentName(calculatorPackage, calculatorClass)
            startActivity(intent)
        }


        val navView: NavigationView = findViewById(R.id.nav_view)

        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                    Toast.makeText(applicationContext, "Welcome!", Toast.LENGTH_SHORT).show()
                }
                R.id.myProfile -> {
                    val i = Intent(this, UserProfileActivity::class.java)
                    startActivity(i)
                    Toast.makeText(applicationContext, "My Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.Logout -> {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Toast.makeText(applicationContext, "You have Logged Out.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}