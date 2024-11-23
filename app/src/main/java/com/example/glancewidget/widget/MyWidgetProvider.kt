package com.example.glancewidget.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import com.example.glancewidget.MyWidget // 正しいパスをインポート
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class MyWidgetProvider : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = MyWidget()
}