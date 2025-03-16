package com.example.dogwalk.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogwalk.viewmodel.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = AuthViewModel()) {
    var dogName by remember { mutableStateOf("") }
    var ownerEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var authMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (viewModel.isRegistering) "Rejestracja psa" else "Logowanie",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isRegistering) {
            OutlinedTextField(
                value = dogName,
                onValueChange = {
                    dogName = it
                    viewModel.onDogNameChange(it)
                },
                label = { Text("Imię psa") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = ownerEmail,
            onValueChange = {
                ownerEmail = it
                viewModel.onOwnerEmailChange(it)
            },
            label = { Text("Email właściciela") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.onPasswordChange(it)
            },
            label = { Text("Hasło") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.onSubmit { success, message ->
                    authMessage = message
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (viewModel.isRegistering) "Zarejestruj się" else "Zaloguj się")
        }

        if (authMessage != null) {
            Text(
                text = authMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { viewModel.toggleAuthMode() }) {
            Text(if (viewModel.isRegistering) "Masz konto? Zaloguj się" else "Nie masz konta? Zarejestruj się")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}
