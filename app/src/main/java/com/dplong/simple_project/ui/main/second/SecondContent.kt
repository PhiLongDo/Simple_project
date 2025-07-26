package com.dplong.simple_project.ui.main.second

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.dplong.simple_project.R
import com.dplong.simple_project.ui.compose.AppPieChart

@Composable
internal fun SecondContent(
    onBackClick: () -> Unit = {},
    onFirstClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppPieChart(
            modifier = Modifier
                .heightIn(200.dp, 500.dp)
                .fillMaxWidth(),
            data = listOf(ValueDataEntry("aaa", 10), ValueDataEntry("bbb", 20))
        )
        Box(
            modifier = Modifier
                .clickable(
                    onClick = onFirstClick,
                )
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Cyan, shape = CircleShape)
                .background(color = Color.Black.copy(alpha = 0.75f))
        )
        {
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                text = stringResource(R.string.first_screen),
                style = TextStyle(color = Color.White),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .clickable(
                    onClick = onBackClick,
                )
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Cyan, shape = CircleShape)
                .background(color = Color.Black.copy(alpha = 0.75f))
        )
        {
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                text = stringResource(R.string.back),
                style = TextStyle(color = Color.White),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun SecondContent(vm: SecondViewModel) {
    SecondContent(onBackClick = vm::onBackClick, onFirstClick = vm::onFirstClick)
}