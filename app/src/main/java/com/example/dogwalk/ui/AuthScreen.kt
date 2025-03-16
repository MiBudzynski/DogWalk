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
                value = viewModel.dogName,
                onValueChange = { viewModel.onDogNameChange(it) },
                label = { Text("Imię psa") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = viewModel.ownerEmail,
            onValueChange = { viewModel.onOwnerEmailChange(it) },
            label = { Text("Email właściciela") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Hasło") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onSubmit() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (viewModel.isRegistering) "Zarejestruj się" else "Zaloguj się")
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
