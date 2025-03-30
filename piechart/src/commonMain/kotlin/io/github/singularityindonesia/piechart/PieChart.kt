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
package io.github.singularityindonesia.piechart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import io.github.singularityindonesia.chartcore.compose.LocalFullCircleDegree
import io.github.singularityindonesia.chartcore.compose.LocalPieThicknessPercent
import io.github.singularityindonesia.chartcore.compose.LocalStartAngleDegree
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.chartcore.util.ifNull

typealias StartAngleDegree = Float

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    items: List<ChartItem>,
) {
    val startAngleDegree = LocalStartAngleDegree.current
    val fullCircleDegree = LocalFullCircleDegree.current
    val thickness = LocalPieThicknessPercent.current

    // map items into list of start angle vs pie slices
    val startAngleVsPieSlices by remember(items) {
        val totalWeight = items.mapNotNull { it.values.firstOrNull()?.toFloat() }.sum()

        val slices = items.fold(emptyList<Pair<StartAngleDegree, PieItem>>()) { acc, i ->
            val startAngle = acc.map { it.second.sweepAngleDegrees }.sum()
            val sweepAngle =
                i.values.firstOrNull()?.toFloat().ifNull { 0f } / totalWeight * 360f
            val pieSlices = PieItem(
                item = i,
                sweepAngleDegrees = sweepAngle,
            )

            acc + (startAngle to pieSlices)
        }
        derivedStateOf { slices }
    }

    Canvas(modifier = modifier) {
        val diameter = minOf(size.width, size.height)
        val radius = diameter / 2f
        val offsetCentralizer = Offset(
            x = if (size.width > size.height) center.x - radius else 0f,
            y = if (size.height > size.width) center.y - radius else 0f,
        )

        val pathVsColor = startAngleVsPieSlices.map { startAngleVsPieSlice ->
            val startAngle = startAngleVsPieSlice.first
            val pie = startAngleVsPieSlice.second
            val legend = startAngleVsPieSlice.second.item.legend
            val sweepAngleDegrees = pie.sweepAngleDegrees

            pie(
                offset = offsetCentralizer,
                outerArcDiameter = diameter,
                innerArcDiameter = diameter * (1 - thickness),
                startAngleDegrees = startAngle * (fullCircleDegree / 360f) + startAngleDegree,
                sweepAngleDegrees = sweepAngleDegrees * (fullCircleDegree / 360f),
            ) to legend.color
        }


        drawIntoCanvas {
            pathVsColor.map { pvc ->
                drawPath(
                    path = pvc.first,
                    color = pvc.second,
                )
            }
        }
    }
}
