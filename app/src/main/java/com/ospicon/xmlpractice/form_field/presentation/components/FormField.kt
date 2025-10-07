package com.ospicon.xmlpractice.form_field.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ospicon.xmlpractice.ui.theme.XmlPracticeTheme

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
            )
        },
        value = value,
        onValueChange = onValueChange,
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FromFieldPreview() {
    XmlPracticeTheme {
        var username by remember { mutableStateOf("") }

        FormField(
            label = "帳號",
            value = username,
            onValueChange = { inputText ->
                username = inputText
            }
        )
    }
}