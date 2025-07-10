//package com.example.nye_175_173_login_signup
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.nye_175_173_login_signup.ui.theme.NYE_175_173_login_signupTheme
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import com.example.nye_175_173_login_signup.screen.navigation.AppNavigation
//import com.google.firebase.FirebaseApp
//
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        setContent {
//            NYE_175_173_login_signupTheme{
//                Surface(color = MaterialTheme.colorScheme.background) {
//                    AppNavigation()
//                }
//            }
//        }
//    }
//}
//


//above was old

package com.example.nye_175_173_login_signup

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nye_175_173_login_signup.screen.navigation.AppNavigation
import com.example.nye_175_173_login_signup.ui.theme.NYE_175_173_login_signupTheme
import com.example.nye_175_173_login_signup.util.LocaleUtils
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {

    // Apply selected language before app loads UI
    override fun attachBaseContext(newBase: Context) {
        val newLocaleContext = LocaleUtils.setLocale(newBase, getSavedLanguage(newBase))
        super.attachBaseContext(newLocaleContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NYE_175_173_login_signupTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }

    // Load stored language (defaults to English if not set)
    private fun getSavedLanguage(context: Context): String {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return prefs.getString("language", "en") ?: "en"
    }
}
