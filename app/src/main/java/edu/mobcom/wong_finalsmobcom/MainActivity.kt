package edu.mobcom.wong_finalsmobcom


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.mobcom.wong_finalsmobcom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProceed.setOnClickListener {
            val  i = Intent (this, LoginActivity::class.java)
            startActivity(i)
        }
    }


}