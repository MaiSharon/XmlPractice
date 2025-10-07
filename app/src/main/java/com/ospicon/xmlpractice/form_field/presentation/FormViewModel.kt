package com.ospicon.xmlpractice.form_field.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ospicon.xmlpractice.form_field.presentation.models.FormUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormViewModel : ViewModel() {

    companion object {
        const val TAG = "FormViewModel"
    }

    // 資料留
    private val _uiState = MutableStateFlow(FormUi())
    val uiState = _uiState.asStateFlow()

    // 按下送出時候才做驗證 收集狀態再做驗證
    fun onUsernameChange(username: String) {
        _uiState.update {
            it.copy(username = username)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    private fun validateForm(): Boolean {
        val current = _uiState.value

        // 驗證 成功或者失敗
        val usernameResult = FormValidator.validatorUsername(current.username)
        val passwordResult = FormValidator.validatorPassword(current.password)

        // 收集提示
        _uiState.update {
            it.copy(
                usernameError = (usernameResult as? ValidationResult.Error)?.message,
                passwordError = (passwordResult as? ValidationResult.Error)?.message
            )
        }

        return usernameResult is ValidationResult.Success && passwordResult is ValidationResult.Success
    }


    fun onSubmit() {
        if (!validateForm()) {
            Log.d(TAG, "驗證不通過")
            return
        }

        Log.d(TAG, "驗證通過")

//        // 收集所有狀態並驗證
//        val current = _uiState.value
//
//        // 驗證
//        val validationResults = listOf(
//            "username" to FormValidator.validatorUsername(current.username),
//            "password" to FormValidator.validatorPassword(current.password)
//        )
//
//        val errors: Map<String, String?> = validationResults.associate { (field, result) ->
//            field to (result as? ValidationResult.Error)?.message
//        }
//
//        _uiState.update {
//            it.copy(
//                usernameError = errors["username"],
//                passwordError = errors["password"]
//            )
//        }
//        Log.d("123","errors.values ${errors.values}")
//
//        if (errors.values.any { it != null }) {
//            Log.d("123","有任何一個不為Ｎull 驗證不通過")
//            return
//        }

    }
}