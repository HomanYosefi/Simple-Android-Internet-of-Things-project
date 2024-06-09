package com.example.hsport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun setting(navController: NavHostController) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "تنظیمات", style = MaterialTheme.typography.h5, modifier = Modifier.padding(bottom = 8.dp))
            Divider()

            SettingItem(title = "تنظیمات دزدگیر", summary = "تنظیمات مربوط به دزدگیر هوشمند")
            Divider()

            SettingItem(title = "تنظیمات اعلان", summary = "تنظیمات مربوط به اعلان‌ها و هشدارها")
            Divider()

            SettingItem(title = "تنظیمات حساب کاربری", summary = "تنظیمات مربوط به حساب کاربری شما")
            Divider()
        }
}


@Composable
fun SettingItem(title: String, summary: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.subtitle1)
            Text(text = summary, style = MaterialTheme.typography.body2)
        }
        IconButton(onClick = { /* TODO: Handle click */ }) {
            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}