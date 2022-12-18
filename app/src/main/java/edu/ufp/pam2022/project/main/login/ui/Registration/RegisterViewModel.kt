package edu.ufp.pam2022.project.main.login.ui.Registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.main.login.ui.login.LoginFormState
import edu.ufp.pam2022.project.main.login.ui.login.LoginResult
import edu.ufp.pam2022.project.services.HttpService

class RegisterViewModel (private val loginRepository: HttpService) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun register(Username:String,Email: String, Password: String) {
        // can be launched in a separate asynchronous job
        val result =loginRepository.register(Username,Email,Password)

    }

    fun registerDataChanged(username:String,email: String, password: String) {
       if (!isUsernamevalid(username)) {
           _loginForm.value = LoginFormState(UsernameError = R.string.invalid_username)
    }else if (!isEmailvalid(email)) {
            _loginForm.value = LoginFormState(EmailError = R.string.invalid_Email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUsernamevalid(username: String): Boolean {
        return username.length > 5
    }

    // A placeholder username validation check
    private fun isEmailvalid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
