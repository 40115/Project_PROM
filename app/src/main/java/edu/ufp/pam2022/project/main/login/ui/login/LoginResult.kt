package edu.ufp.pam2022.project.main.login.ui.login

import edu.ufp.pam2022.project.library.User

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: User? = null,
    val error: Int? = null
)