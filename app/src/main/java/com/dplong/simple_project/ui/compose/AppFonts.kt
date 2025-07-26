package com.dplong.simple_project.ui.compose

import androidx.annotation.FontRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily

import androidx.core.content.res.ResourcesCompat

@Composable
fun wsFontResource(@FontRes id: Int): FontFamily {
    val context = LocalContext.current
    return remember {
        FontFamily(
            typeface = ResourcesCompat.getFont(context, id)!!
        )
    }
}