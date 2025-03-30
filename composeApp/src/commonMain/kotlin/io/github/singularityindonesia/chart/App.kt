package io.github.singularityindonesia.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.chartcore.record.Legend
import io.github.singularityindonesia.chart.page.PieChartPage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val chartItem = remember { getItems() }
    val page by remember { mutableStateOf(Page.PieChart) }

    MaterialTheme {
        Scaffold(
            topBar = {
                Text(
                    text = page.pageTitle,
                    style = MaterialTheme.typography.h4
                )
            },
            bottomBar = {
                BottomNavigation {

                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                when (page) {
                    Page.PieChart -> PieChartPage(items = chartItem)
                }
            }
        }
    }
}

fun getItems(): List<ChartItem> {
    val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta)

    return (0..5 step 1).map {
        ChartItem(
            id = it.toString(),
            value = it,
            legend = Legend(
                label = "Item $it",
                color = colors[it % 5],
            )
        )
    }
}