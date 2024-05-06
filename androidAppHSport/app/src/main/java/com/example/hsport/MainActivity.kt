package com.example.hsport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.material.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.PrintStream
import java.net.Socket

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { CoroutineScope(Dispatchers.IO).launch { sendMessage("lock") } }){
                        Text("lock")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { CoroutineScope(Dispatchers.IO).launch { sendMessage("unlock") } }){
                        Text("unlock")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { CoroutineScope(Dispatchers.IO).launch { sendMessage("Siren") } }){
                        Text("Siren")
                    }
                }
            }
        }
    }
}






suspend fun sendMessage(message: String) {
    try {
        Socket("192.168.1.100", 8080).use { socket ->
            PrintStream(socket.getOutputStream()).use { output ->
                output.print(message)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}