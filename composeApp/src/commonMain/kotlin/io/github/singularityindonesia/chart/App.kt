package io.github.singularityindonesia.chart

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.singularityindonesia.chart.Page.LineChart
import io.github.singularityindonesia.chart.Page.PieChart
import io.github.singularityindonesia.chart.page.LineChartPage
import io.github.singularityindonesia.chart.page.PieChartPage
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.chartcore.record.Legend
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
@Preview
fun App() {
    val chartItem = remember { getItems() }
    var page by remember { mutableStateOf<Page>(PieChart) }
    val pages = remember {
        listOf(PieChart, LineChart)
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                Text(
                    text = page.pageTitle,
                    style = MaterialTheme.typography.h4
                )
            },
            bottomBar = {
                TabRow(
                    selectedTabIndex = pages.indexOf(page)
                ) {
                    pages.map {
                        Tab(
                            selected = it == page,
                            onClick = {
                                page = it
                            }
                        ) {
                            Text(it.pageTitle)
                        }
                    }
                }
            }
        ) {
            when (page) {
                PieChart -> PieChartPage(modifier = Modifier.padding(it), items = chartItem)
                LineChart -> LineChartPage(modifier = Modifier.padding(it), items = chartItem)
            }
        }
    }
}

fun getItems(): List<ChartItem> {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)

    return (0..4 step 1).map {
        ChartItem(
            id = it.toString(),
            values = listOf(
                it, // x axis
                it, // y axis
                it, // z axis
                Random.nextInt(1, 10).toFloat() // value
            ),
            legend = Legend(
                label = "Item $it",
                color = colors[it % 5],
            )
        )
    }
}