package com.example.nye_175_173_login_signup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { e ->
                val message = when (e) {
                    is FirebaseAuthInvalidUserException -> "Account not found. Please sign up."
                    is FirebaseAuthInvalidCredentialsException -> "Invalid credentials"
                    else -> e.message ?: "Login failed"
                }
                onResult(false, message)
            }
    }

    fun register(email: String, password: String, phone: String, username: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = auth.currentUser?.uid ?: return@addOnSuccessListener
                val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                val userMap = mapOf(
                    "email" to email,
                    "phone" to phone,
                    "username" to username
                )
                userRef.setValue(userMap)
                onResult(true, null)
            }
            .addOnFailureListener { e ->
                onResult(false, e.message ?: "Registration failed")
            }
    }
}
