package com.ospicon.xmlpractice

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ospicon.xmlpractice.form_field.presentation.FormRoute
import com.ospicon.xmlpractice.ui.theme.XmlPracticeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XmlPracticeTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//                WorldClocks()

                FormRoute()
            }
        }
    }
}

// --- 時鐘管理 (用 Thread 更新 StateFlow) ---
@RequiresApi(Build.VERSION_CODES.O)
class ClockThread(
    private val zoneId: String
) {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val _timeFlow = MutableStateFlow("--:--:--")
    val timeFlow = _timeFlow.asStateFlow()

    private lateinit var thread: Thread


    fun startTime() {
        thread = Thread {
            while (true) {
                val now = ZonedDateTime.now(ZoneId.of(zoneId))
                _timeFlow.value = now.format(formatter)
                Thread.sleep(1000) // 每秒更新
            }
        }
        thread.isDaemon = true // 避免阻塞退出
        thread.start()
    }

    fun stopTime() {
//        thread.stop()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorldClocks() {
    val clockThread: ClockThread


//    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Button(onClick = { clockThread.startTime() }) {
//            Text("Start")
//        }
//        Button(onClick = { clockThread.stopTime() }) {
//            Text("Stop")
//        }
//    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ClockItem("台灣", ClockThread("Asia/Taipei"))
        ClockItem("巴黎", ClockThread("Europe/Paris"))
        ClockItem("美國 (紐約)", ClockThread("America/New_York"))
        ClockItem("韓國", ClockThread("Asia/Seoul"))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClockItem(label: String, clockThread: ClockThread) {
    val time by clockThread.timeFlow.collectAsState()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Text(text = time, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XmlPracticeTheme {
        Greeting("Android")
    }
}