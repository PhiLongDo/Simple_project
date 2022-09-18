package com.dplong.simple_project.ui.main.first

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dplong.simple_project.R

@Composable
internal fun FirstContent(
    email: String,
    onSecondClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = email)
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .clickable(
                    onClick = onSecondClick,
                )
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Cyan, shape = CircleShape)
                .background(color = Color.Black.copy(alpha = 0.75f))
        )
        {
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                text = stringResource(R.string.second_screen),
                style = TextStyle(color = Color.White),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun FirstContent(vm: FirstViewModel) {
    FirstContent(email = vm.email, onSecondClick = vm::onSecondClick)
}