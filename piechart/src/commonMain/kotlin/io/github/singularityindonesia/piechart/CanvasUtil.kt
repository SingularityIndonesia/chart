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

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path

fun pie(
    offset: Offset,
    outerArcDiameter: Float,
    innerArcDiameter: Float,
    startAngleDegrees: Float,
    sweepAngleDegrees: Float,
): Path {
    val outerArcSize = Size(outerArcDiameter, outerArcDiameter)
    val innerArcSize = Size(innerArcDiameter, innerArcDiameter)
    val outerRadius = outerArcDiameter / 2f
    val innerRadius = innerArcDiameter / 2f
    val radiusDiff = outerRadius - innerRadius

    return Path().apply {
        // draw outside bow
        arcTo(
            rect = Rect(
                offset = offset,
                size = outerArcSize,
            ),
            startAngleDegrees = startAngleDegrees,
            sweepAngleDegrees = sweepAngleDegrees,
            forceMoveTo = false,
        )

        // draw inner bow
        arcTo(
            rect = Rect(
                offset = offset + Offset(radiusDiff, radiusDiff),
                size = innerArcSize,
            ),
            startAngleDegrees = startAngleDegrees + sweepAngleDegrees,
            sweepAngleDegrees = -(sweepAngleDegrees),
            forceMoveTo = false,
        )

        close()
    }
}
