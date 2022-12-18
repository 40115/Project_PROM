package edu.ufp.pam2022.project.main.login.ui.Registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.ufp.pam2022.project.databinding.ActivityRegisterBinding
import edu.ufp.pam2022.project.main.login.ui.login.LoginMainActivity
import edu.ufp.pam2022.project.main.login.ui.login.LoginViewModel
import edu.ufp.pam2022.project.main.login.ui.login.LoginViewModelFactory
import androidx.lifecycle.Observer
import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.library.User
import edu.ufp.pam2022.project.main.login.ui.login.afterTextChanged
import edu.ufp.pam2022.project.services.SoundService

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username=binding.username
        val email = binding.email
        val password = binding.password
        val register = binding.Register
        val loading = binding.loading

        registerViewModel = ViewModelProvider(this, LoginViewModelFactory(this))[registerViewModel::class.java]

        registerViewModel.loginFormState.observe(this@RegisterActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            register.isEnabled = loginState.isDataValid

            if (loginState.UsernameError != null) {
                email.error = getString(loginState.UsernameError)
            }

            if (loginState.EmailError != null) {
                email.error = getString(loginState.EmailError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })
        registerViewModel.loginResult.observe(this@RegisterActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE

            if (loginResult.error != null) {
                showregisterfail(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        startService(Intent(this, SoundService::class.java).
        setAction("0"));

        email.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                registerViewModel.registerDataChanged(
                    username.text.toString(),
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        registerViewModel.register(
                            register.text.toString(),
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

           register.setOnClickListener {
                loading.visibility = View.VISIBLE
                registerViewModel .register(register.text.toString(),email.text.toString(), password.text.toString())
            }
        }

    }

    private fun updateUiWithUser(model: User) {
        val welcome = getString(R.string.welcome)
        val displayName = model.Username
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showregisterfail(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    fun loginMessage() {
        val switchActivityIntent = Intent(this, LoginMainActivity::class.java)
        startActivity(switchActivityIntent)
    }
}


