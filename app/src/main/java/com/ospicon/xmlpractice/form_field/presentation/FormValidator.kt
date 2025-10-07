package com.ospicon.xmlpractice.form_field.presentation


object FormValidator {

    fun validatorUsername(username: String): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult.Error("請輸入使用者名稱")
            username.length < 3 -> ValidationResult.Error("使用者名稱至少 3 個字元")
            else -> ValidationResult.Success
        }
    }

    fun validatorPassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult.Error("請輸入密碼")
            password.length < 3 -> ValidationResult.Error("密碼至少 3 個字元")
            else -> ValidationResult.Success
        }
    }

    fun validatorGender(selected: String, genderList: List<String>): ValidationResult {
        return when {
            selected == genderList[0] -> ValidationResult.Error("請選擇性別")
            else -> ValidationResult.Success
        }
    }
}