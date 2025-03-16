package com.example.dogwalk.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var dogName by mutableStateOf("")
        private set

    var ownerEmail by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isRegistering by mutableStateOf(true)
        private set

    var authMessage by mutableStateOf<String?>(null)
        private set

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

    fun onSubmit(callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            if (isRegistering) {
                register(callback)
            } else {
                login(callback)
            }
        }
    }

    private fun register(callback: (Boolean, String?) -> Unit) {
        if (ownerEmail.isEmpty() || password.length < 6) {
            callback(false, "Email nie może być pusty, a hasło musi mieć min. 6 znaków!")
            return
        }

        auth.createUserWithEmailAndPassword(ownerEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Rejestracja zakończona sukcesem! 🎉")
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    private fun login(callback: (Boolean, String?) -> Unit) {
        if (ownerEmail.isEmpty() || password.isEmpty()) {
            callback(false, "Podaj poprawne dane logowania!")
            return
        }

        auth.signInWithEmailAndPassword(ownerEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Zalogowano pomyślnie! ✅")
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }
}
