package com.ospicon.xmlpractice.form_field.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ospicon.xmlpractice.form_field.presentation.components.FormField
import com.ospicon.xmlpractice.form_field.presentation.models.FormUi
import com.ospicon.xmlpractice.form_field.presentation.FormViewModel
import com.ospicon.xmlpractice.ui.theme.XmlPracticeTheme


@Composable
fun FormRoute(
) {
    val viewModel = viewModel<FormViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FormScreen(
        uiState = uiState,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSubmit = viewModel::onSubmit
    )
}

@Composable
fun FormScreen(
    uiState: FormUi,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        // 帳號
        FormField(
            label = "username",
            value = uiState.username,
            onValueChange = { newInput ->
                onUsernameChange(newInput)
            },
            errorMessage = uiState.usernameError
        )
        // 密碼
        FormField(
            label = "password",
            value = uiState.password,
            onValueChange = { newInput ->
                onPasswordChange(newInput)
            },
            errorMessage = uiState.passwordError
        )
        // 下拉選單
        // 送出
        Button(
            onClick = { onSubmit() },
            Modifier.align(Alignment.End),
        ) {
            Text(text = "submit")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    XmlPracticeTheme {
        FormScreen(
            uiState = FormUi(
//                username = "test_user",
//                password = "password123",
//                usernameError = "sss"
            ),
            onUsernameChange = {},
            onPasswordChange = {},
            onSubmit = {}
        )
    }
}