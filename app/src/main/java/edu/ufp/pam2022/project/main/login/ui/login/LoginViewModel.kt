package edu.ufp.pam2022.project.main.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

import edu.ufp.pam2022.project.R
import edu.ufp.pam2022.project.library.User

import edu.ufp.pam2022.project.services.HttpService

class LoginViewModel(private val loginRepository: HttpService) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(Email: String, Password: String) {
        // can be launched in a separate asynchronous job
        val result =loginRepository.logIn(Email,Password)

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isEmailvalid(username)) {
            _loginForm.value = LoginFormState(EmailError = R.string.invalid_Email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
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
        return password.length > 4
    }
}