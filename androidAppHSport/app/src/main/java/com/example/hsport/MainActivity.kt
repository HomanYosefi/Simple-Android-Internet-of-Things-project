package com.example.hsport

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers



var location_server = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    location_server = sendMessageAndReceiveResponse(message = "hi") ?: ""
                }
                mainScreen(navController)
            }
        }
    }
}



fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}







suspend fun sendMessage(message: String) {
    try {
        val socket = Socket("192.168.14.205", 8080)
        val output = PrintStream(socket.getOutputStream())
        val input = BufferedReader(InputStreamReader(socket.getInputStream()))

        output.println(message)
        output.flush()

        val response = input.readLine()
        println("Response: $response")

        input.close()
        output.close()
        socket.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

suspend fun sendMessageAndReceiveResponse(message: String): String? {
    try {
        val socket = Socket("192.168.14.205", 8080)
        val output = PrintStream(socket.getOutputStream())
        val input = BufferedReader(InputStreamReader(socket.getInputStream()))

        output.println(message)
        output.flush()

        val response = input.readLine()
        input.close()
        output.close()
        socket.close()
        return response
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun parseCarStatus(response: String): CarStatus {
    val jsonObject = JSONObject(response)
    return CarStatus(
        doors = jsonObject.getString("doors"),
        engine = jsonObject.getString("engine"),
        lights = jsonObject.getString("lights"),
        windows = jsonObject.getString("windows"),
        fuelLevel = jsonObject.getString("fuel_level"),
        waterTemp = jsonObject.getString("water_temp"),
        tirePressure = jsonObject.getString("tire_pressure"),
        insuranceExpiry = jsonObject.getString("insurance_expiry"),
        geoLocation = jsonObject.getString("geo_location")
    )
}



data class CarStatus(
    var doors: String,
    var engine: String,
    var lights: String,
    var windows: String,
    var fuelLevel: String,
    var waterTemp: String,
    var tirePressure: String,
    var insuranceExpiry: String,
    var geoLocation: String
)