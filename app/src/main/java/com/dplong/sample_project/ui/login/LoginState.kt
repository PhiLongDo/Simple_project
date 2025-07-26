package com.dplong.sample_project.ui.login

data class LoginState(
    val email: String,
    val password: String,
) {
    companion object {
        val Empty = LoginState(email = "", password = "")
    }
}