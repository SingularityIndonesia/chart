/*
 * This file is part of Chart.
 * Copyright (C) 2025 singularity.indonesia@gmail.com
 *
 * Chart is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Chart is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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