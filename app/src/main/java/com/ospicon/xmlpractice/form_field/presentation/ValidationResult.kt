package com.ospicon.xmlpractice.form_field.presentation

sealed class ValidationResult {
    data class Error(val message: String) : ValidationResult()
    data object Success : ValidationResult()
}