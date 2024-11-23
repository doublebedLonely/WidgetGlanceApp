package com.example.glancewidget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.appcompat.app.AppCompatActivity
import androidx.glance.appwidget.GlanceAppWidgetManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }

    @Composable
    fun MainContent() {
        val context = LocalContext.current

        Box {
            Text(text = "ホーム画面にウィジェットを追加")
        }
    }

    companion object {
        private const val APPWIDGET_REQUEST_CODE = 1
    }
}

