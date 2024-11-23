package com.example.glancewidget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.unit.ColorProvider
import com.example.glancewidget.utils.TrainTime
import com.example.glancewidget.utils.getNextTwoDirectTrains
import com.example.glancewidget.utils.loadTrainTimes

class MyWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Responsive(setOf(DpSize(20.dp, 20.dp), DpSize(30.dp, 30.dp)))

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val timesList = loadTrainTimes(context)

        val wNextTrain = getNextTwoDirectTrains(timesList)

        println(wNextTrain)

        provideContent {
            FromKarakidaWidgetContent(wNextTrain,context)
        }
    }

}

class TrainTimeWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyWidget()
}


@Composable
fun FromKarakidaWidgetContent(pair: Pair<TrainTime?, TrainTime?>, context: Context) {

    val fontSizeWoSp = 16
    val fontSize = 16.sp

    Column(
        modifier = GlanceModifier.fillMaxSize().padding(4.dp).clickable(actionRunCallback<UpdateTrainAction>()).background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        )
    {
        // 先発電車の情報を表示
        pair.first?.let {
            Row(modifier = GlanceModifier.padding(bottom = 4.dp)) {
                Text(
                    text = it.time,
                    style = TextStyle(color = ColorProvider(Color.White), fontSize = fontSize, fontFamily = FontFamily.Serif )
                )
//                Image(provider = ImageProvider(R.drawable.shinjukuicon),
//                    contentDescription = null,
//                    modifier = GlanceModifier.size(16.dp).
//                )
                Column(
                    modifier = GlanceModifier.cornerRadius(8.dp).background(Color.White)
                ){
                    Text("OH\n01", style = TextStyle(color = ColorProvider(Color.Magenta),fontSize=(fontSizeWoSp/2-2).sp), modifier = GlanceModifier.padding(start = 0.dp))
                }
                Text(
                    text = "新宿",
                    style = TextStyle(color = ColorProvider(Color.White), fontSize = fontSize)
                )
            }
        }
        println(pair)

        // 次発電車の情報を表示
        pair.second?.let {
            Row(modifier = GlanceModifier.padding(top = 4.dp)) {
                Text(
                    text = "次発: ",
                    style = TextStyle(color = ColorProvider(Color.Gray), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )
                Text(
                    text = it.time,
                    style = TextStyle(color = ColorProvider(Color.Gray), fontSize = 16.sp)
                )
            }
        }
    }
}
