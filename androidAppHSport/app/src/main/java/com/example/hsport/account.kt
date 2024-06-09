package com.example.hsport

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kharidino.R

@Composable
fun account(navController: NavHostController) {

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(61, 50, 142, 255), Color(130, 132, 130)),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "وضعیت خودرو",
                style = MaterialTheme.typography.h5.copy(color = Color.White),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(255, 255, 255, 255))
                ) {
                    Column (Modifier.fillMaxSize()){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tier),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(16.dp)
                            )
                            Text(text = "باد تایرها", style = MaterialTheme.typography.subtitle1)
                        }
                        Row (
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "خوب", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(255, 255, 255, 255))
                ) {
                    Column (Modifier.fillMaxSize()){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bme),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(16.dp)
                            )
                            Text(text = "انقضا بیمه", style = MaterialTheme.typography.subtitle1)
                        }
                        Row (
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "کمتر از یکماه", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(255, 255, 255, 255))
                ) {
                    Column (Modifier.fillMaxSize()){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.water),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(16.dp)
                            )
                            Text(text = "دمای آب", style = MaterialTheme.typography.subtitle1)
                        }
                        Row (
                            Modifier.fillMaxWidth().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            LinearProgressIndicatorSample(0.4f)
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(255, 255, 255, 255))
                ) {
                    Column (Modifier.fillMaxSize()){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.oil),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(16.dp)
                            )
                            Text(text = "مقدار سوخت", style = MaterialTheme.typography.subtitle1)
                        }
                        Row (
                            Modifier.fillMaxWidth().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            LinearProgressIndicatorSample(0.7f)
                        }
                    }
                }
            }




            StatusCard(title = "شنیدن صدای درون اتاق", status = "میتوانید گفتگو ها را شنود کنید")
            StatusCard(
                title = "دیدن اطراف",
                status = "با دوربین های جانبی میتوانید چهار طرف خودرو را تماشا کنید"
            )
        }
    }

}


@Composable
fun LinearProgressIndicatorSample(percentage: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${(percentage * 100).toInt()}%",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LinearProgressIndicator(
            progress = percentage,
            color = Color(64,56,139,255),
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(24.dp)
        )
    }
}



@Composable
fun StatusCard(title: String, status: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.subtitle1)
                Text(text = status, style = MaterialTheme.typography.body2)
            }
            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}