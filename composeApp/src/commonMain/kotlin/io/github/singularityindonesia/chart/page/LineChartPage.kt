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
package io.github.singularityindonesia.chart.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.unit.dp
import io.github.singularityindonesia.chartcore.compose.LocalColorFilter
import io.github.singularityindonesia.chartcore.compose.LocalLineWidth
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.linechart.LineChart

@Composable
fun LineChartPage(
    modifier: Modifier = Modifier,
    items: List<ChartItem>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // region Normal Line Chart
        LineChart(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .border(BorderStroke(1.dp, Color.Black))
                .padding(8.dp)
                .then(modifier),
            items = items
        )
        // endregion

        // region Red Line Chart
        // filter line color into red
        val redLineFilter = remember {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        1f, 1f, 1f, 1f, 1f,  // Red channel -> 1f
                        0f, 0f, 0f, 0f, 0f,  // Green channel -> 0
                        0f, 0f, 0f, 0f, 0f,  // Blue channel -> 0
                        0f, 0f, 0f, 1f, 0f   // Alpha channel remains unchanged
                    )
                )
            )
        }

        CompositionLocalProvider(
            LocalColorFilter provides redLineFilter,
        ) {
            LineChart(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .border(BorderStroke(1.dp, Color.Black))
                    .padding(8.dp)
                    .then(modifier),
                items = items
            )
        }
        // endregion

        // region Blue Line Chart
        // filter line color into red
        val blueLineFilter = remember {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0f, 0f, 0f, 0f, 0f,  // Red channel -> 0
                        0f, 0f, 0f, 0f, 0f,  // Green channel -> 0
                        1f, 1f, 1f, 1f, 1f,  // Blue channel -> 1f
                        0f, 0f, 0f, 1f, 0f   // Alpha channel remains unchanged
                    )
                )
            )
        }

        CompositionLocalProvider(
            LocalColorFilter provides blueLineFilter,
            LocalLineWidth provides 10.dp
        ) {
            LineChart(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .border(BorderStroke(1.dp, Color.Black))
                    .padding(8.dp)
                    .then(modifier),
                items = items
            )
        }
        // endregion
    }
}