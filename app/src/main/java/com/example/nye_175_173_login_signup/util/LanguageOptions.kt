package com.example.nye_175_173_login_signup.util



enum class LanguageOptions(val code: String) {
    ENGLISH("en"),
    HINDI("hi");

    override fun toString(): String {
        return when (this) {
            ENGLISH -> "English"
            HINDI -> "हिन्दी"
        }
    }
}
