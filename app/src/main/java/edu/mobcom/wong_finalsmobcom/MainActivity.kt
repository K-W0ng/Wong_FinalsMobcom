package edu.mobcom.wong_finalsmobcom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var proceedMain: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proceedMain = findViewById(R.id.btnProceed)

        fun openLoginActivity(){
            val iLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(iLoginActivity)
        }

        proceedMain.setOnClickListener {
            openLoginActivity()
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}