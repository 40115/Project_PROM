package edu.ufp.pam2022.project.main.login.ui.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.main.login.ui.login.LoginMainActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun loginMessage(view: View) {
        val switchActivityIntent = Intent(this, LoginMainActivity::class.java)
        startActivity(switchActivityIntent)
    }
}