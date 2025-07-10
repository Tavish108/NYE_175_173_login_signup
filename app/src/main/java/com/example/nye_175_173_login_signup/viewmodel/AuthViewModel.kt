//package com.example.nye_175_173_login_signup.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
//import com.google.firebase.auth.FirebaseAuthInvalidUserException
//import com.google.firebase.database.FirebaseDatabase
//
//class AuthViewModel : ViewModel() {
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//
//    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//                onResult(true, null)
//            }
//            .addOnFailureListener { e ->
//                val message = when (e) {
//                    is FirebaseAuthInvalidUserException -> "Account not found. Please sign up."
//                    is FirebaseAuthInvalidCredentialsException -> "Invalid credentials"
//                    else -> e.message ?: "Login failed"
//                }
//                onResult(false, message)
//            }
//    }
//
//    fun register(email: String, password: String, phone: String, username: String, onResult: (Boolean, String?) -> Unit) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//                val userId = auth.currentUser?.uid ?: return@addOnSuccessListener
//                val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
//                val userMap = mapOf(
//                    "email" to email,
//                    "phone" to phone,
//                    "username" to username
//                )
//                userRef.setValue(userMap)
//                onResult(true, null)
//            }
//            .addOnFailureListener { e ->
//                onResult(false, e.message ?: "Registration failed")
//            }
//    }
//}

//above was of firebase and now below is custom




package com.example.nye_175_173_login_signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nye_175_173_login_signup.model.AuthRequest
import com.example.nye_175_173_login_signup.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = RetrofitClient.api.signup(AuthRequest(email, password))
                if (res.isSuccessful) {
                    _message.value = res.body() ?: "Signup successful"
                } else {
                    _message.value = res.errorBody()?.string() ?: "Signup failed"
                }
            } catch (e: Exception) {
                _message.value = "❌ Error: ${e.localizedMessage}"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = RetrofitClient.api.login(AuthRequest(email, password))
                if (res.isSuccessful) {
                    val token = res.body()?.token
                    _token.value = token
                    _message.value = if (token != null) "✅ Login successful" else "⚠️ Empty token"
                } else {
                    _message.value = res.errorBody()?.string() ?: "Login failed"
                }
            } catch (e: Exception) {
                _message.value = "❌ Error: ${e.localizedMessage}"
            }
        }
    }

    fun clearMessages() {
        _message.value = null
        _token.value = null
    }
}
