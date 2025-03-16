package com.example.dogwalk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AuthViewModel : ViewModel() {
    var dogName by mutableStateOf("")
    var ownerEmail by mutableStateOf("")
    var password by mutableStateOf("")
    var isRegistering by mutableStateOf(true) // Przełączanie między logowaniem a rejestracją

    fun toggleAuthMode() {
        isRegistering = !isRegistering
    }

    fun onDogNameChange(newValue: String) {
        dogName = newValue
    }

    fun onOwnerEmailChange(newValue: String) {
        ownerEmail = newValue
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
    }

    fun onSubmit() {
        if (isRegistering) {
            // Logika rejestracji psa
            println("Rejestracja psa: $dogName, Właściciel: $ownerEmail")
        } else {
            // Logika logowania
            println("Logowanie: Właściciel: $ownerEmail")
        }
    }
}
