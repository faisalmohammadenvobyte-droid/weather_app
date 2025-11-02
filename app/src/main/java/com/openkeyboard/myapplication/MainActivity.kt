package com.openkeyboard.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.openkeyboard.myapplication.ui.home.HomeScreen
import com.openkeyboard.myapplication.ui.theme.MyApplicationTheme
import com.openkeyboard.myapplication.utils.location.permission.LocationPermissionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {




            }
        }
    }
}





