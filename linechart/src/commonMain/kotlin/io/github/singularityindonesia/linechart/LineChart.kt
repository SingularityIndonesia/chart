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
package io.github.singularityindonesia.linechart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import io.github.singularityindonesia.chartcore.compose.LocalColorFilter
import io.github.singularityindonesia.chartcore.compose.LocalLineWidth
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.chartcore.util.ifNull

/**
 * Plot a line chart for the given chart items.
 * @param items a list of chartItem. It's important that the chart item must hold a list of 2 values--since
 * line chart is using 2-dimensional cartesian plot, you need to have at least 2 values to represent x value and y value.
 * the x value is always `value.first` and the y value is always `value.second`
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    items: List<ChartItem>,
) {
    val colorFilter = LocalColorFilter.current
    val lineWidth = LocalLineWidth.current

    Canvas(modifier = modifier) {
        val xScale = size.width / items.mapNotNull { it.values.firstOrNull()?.toFloat() }.max()
        val yScale = size.height / items.mapNotNull { it.values.lastOrNull()?.toFloat() }.max()

        val path = Path().apply {
            items.forEachIndexed { i, e ->
                if (i == items.size - 1) return@apply

                val nextItem = items[i + 1]
                val startOffset = Offset(
                    e.values.firstOrNull()?.toFloat().ifNull { 0f } * xScale,
                    size.height - e.values.lastOrNull()?.toFloat().ifNull { 0f } * yScale
                )
                val endOffset = Offset(
                    nextItem.values.firstOrNull()?.toFloat().ifNull { 0f } * xScale,
                    size.height - nextItem.values.lastOrNull()?.toFloat().ifNull { 0f } * yScale
                )

                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(e.legend.color, nextItem.legend.color),
                        start = startOffset,
                        end = endOffset
                    ),
                    colorFilter = colorFilter,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = lineWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }

        drawPath(path = path, color = Color.Blue)
    }
}
