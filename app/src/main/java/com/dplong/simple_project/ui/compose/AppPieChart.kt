package com.dplong.simple_project.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout


@Composable
fun AppPieChart(modifier: Modifier = Modifier, title: String? = null, data: List<DataEntry>) {


    AndroidView(factory = { context ->
        val anyChartView = AnyChartView(context)
        val pie = AnyChart.pie()
        pie.data(data)
        pie.labels().position("inside")
        pie.title(title)

        pie.legend().title().enabled(false)
        pie.legend().title()
            .text("Retail channels")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        anyChartView.setChart(pie)
        anyChartView
    }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun PreviewPisChart() {
    AppPieChart(data = listOf(ValueDataEntry("aaa", 10), ValueDataEntry("bbb", 20)))
}