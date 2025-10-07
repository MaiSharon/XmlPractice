package com.ospicon.xmlpractice.form_field.presentation.models

data class FormUi(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)