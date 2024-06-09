package com.example.hsport

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kharidino.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun home(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("HSport               ")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("notification") }) {
                        Icon(Icons.Filled.Notifications, contentDescription = null)
                    }
                },
                backgroundColor = Color(244, 243, 248, 255),
                contentColor = Color(61, 50, 142, 255),
                elevation = 0.dp
            )
        }
    ) {
        Spacer(modifier = Modifier.padding(it))

        val image = painterResource(id = R.drawable.car)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(244, 243, 248, 255)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Row {
                CustomToggle()
            }
            Row {
                ButtonCar()
            }
            Row {
                headlites()
            }
            Row {
                glassCar()
            }
        }
    }
}


@Composable
fun glassCar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(255, 255, 255))
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.glass),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color(61, 52, 143, 255)),
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
            )
            //Spacer(modifier = Modifier.weight(0.1f))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "شیشه", style = MaterialTheme.typography.subtitle1)
                Text(text = "کنترل تمامی شیشه ها", style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier
                        .size(60.dp)
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun headlites() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(255, 255, 255))
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.headlite),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
            )
            //Spacer(modifier = Modifier.weight(0.1f))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "چراغ ها", style = MaterialTheme.typography.subtitle1)
                Text(text = "کنترل تمامی چراغ ها", style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier
                        .size(60.dp)
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ButtonCar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(255, 255, 255))
    ) {
        Row(modifier = Modifier.padding(top = 30.dp)) {
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // ارسال پیام به سرور
                        CoroutineScope(Dispatchers.IO).launch {
                            sendMessage("network")
                        }
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(245, 245, 245, 255)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wifi),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text("شبکه", modifier = Modifier.padding(top = 10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // ارسال پیام به سرور
                        CoroutineScope(Dispatchers.IO).launch {
                            sendMessage("trunk")
                        }
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(245, 245, 245, 255)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sandogh),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text("صندوق پران", modifier = Modifier.padding(top = 10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            val buttonColorVoice = remember { mutableStateOf(Color(245, 245, 245, 255)) }
            val buttonIconVoice = remember { mutableStateOf(Color(195,195,195,255)) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (buttonColorVoice.value == Color(245, 245, 245, 255)) {
                            buttonColorVoice.value = Color(180, 171, 211)
                            buttonIconVoice.value = Color(61,51,150,255)
                        } else {
                            buttonColorVoice.value = Color(245, 245, 245, 255)
                            buttonIconVoice.value = Color(195,195,195,255)
                        }
                        // ارسال پیام به سرور
                        CoroutineScope(Dispatchers.IO).launch {
                            sendMessage(if (buttonColorVoice.value == Color(180, 171, 211)) "voice_on" else "voice_off")
                        }
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColorVoice.value
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.voice),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(buttonIconVoice.value),
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(37.dp)
                    )
                }
                Text("آژیر", modifier = Modifier.padding(top = 10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            val buttonColor = remember { mutableStateOf(Color(245, 245, 245, 255)) }
            val buttonIcon = remember { mutableStateOf(Color(195,195,195,255)) }
            val buttonText = remember { mutableStateOf("خاموش") }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (buttonColor.value == Color(245, 245, 245, 255)) {
                            buttonColor.value = Color(180, 171, 211)
                            buttonIcon.value = Color(61,51,150,255)
                            buttonText.value = "روشن"
                        } else {
                            buttonColor.value = Color(245, 245, 245, 255)
                            buttonIcon.value = Color(195,195,195,255)
                            buttonText.value = "خاموش"
                        }
                        // ارسال پیام به سرور
                        CoroutineScope(Dispatchers.IO).launch {
                            sendMessage(if (buttonColor.value == Color(180, 171, 211)) "turn_on" else "turn_off")
                        }
                    },
                    modifier = Modifier
                        .size(70.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor.value
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.off),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(buttonIcon.value),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(buttonText.value, modifier = Modifier.padding(top = 10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CustomToggle() {
    var isToggled by remember { mutableStateOf(false) }

    // استفاده از animateDpAsState برای انیمیشن جابجایی افقی
    val alignment by animateDpAsState(targetValue = if (isToggled) 80.dp else 0.dp, label = "")

    Box(
        modifier = Modifier
            .height(50.dp)
            .width(160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(255, 255, 255, 255))
            .clickable {
                isToggled = !isToggled
                // ارسال پیام به سرور
                CoroutineScope(Dispatchers.IO).launch {
                    sendMessage(if (isToggled) "lock" else "unlock")
                }
            }
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .offset(x = alignment)
                .clip(RoundedCornerShape(50.dp))
                .height(45.dp)
                .width(70.dp)
                .background(Color(61, 52, 145, 255))
                .animateContentSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isToggled) {
                Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.White)
            } else {
                Image(
                    painter = painterResource(id = R.drawable.unlock2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(23.dp)
                )
            }
        }
    }
}