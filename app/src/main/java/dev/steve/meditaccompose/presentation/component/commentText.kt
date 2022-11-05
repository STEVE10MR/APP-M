package dev.steve.meditaccompose.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun commentText(
    text:String,position: TextAlign=TextAlign.Start)
{
    Text(text = text, style = MaterialTheme.typography.body2.copy(
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        textAlign = position), modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp))

}