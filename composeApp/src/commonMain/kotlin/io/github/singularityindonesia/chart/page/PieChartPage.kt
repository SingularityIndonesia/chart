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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.unit.dp
import io.github.singularityindonesia.chartcore.compose.LocalColorFilter
import io.github.singularityindonesia.chartcore.record.ChartItem
import io.github.singularityindonesia.chartcore.compose.LocalFullCircleDegree
import io.github.singularityindonesia.chartcore.compose.LocalPieThicknessPercent
import io.github.singularityindonesia.chartcore.compose.LocalStartAngleDegree
import io.github.singularityindonesia.piechart.PieChart

@Composable
fun PieChartPage(
    modifier: Modifier = Modifier,
    items: List<ChartItem>
) {
    var animate by remember { mutableStateOf(false) }
    val valueAnimator by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        animationSpec = tween(2000)
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PieChart(
                modifier = Modifier.size(200.dp),
                items = items
            )
        }
        item {
            // filter color into gray scale
            val grayScaleFilter = remember {
                ColorFilter.colorMatrix(
                    ColorMatrix().apply {
                        setToSaturation(0f)
                    }
                )
            }
            CompositionLocalProvider(
                LocalColorFilter provides grayScaleFilter
            ) {
                PieChart(
                    modifier = Modifier.size(200.dp),
                    items = items
                )
            }
        }
        item {
            // With start angle animation
            CompositionLocalProvider(
                LocalStartAngleDegree provides (valueAnimator * 360f) - 90f,
            ) {
                PieChart(
                    modifier = Modifier.size(200.dp),
                    items = items
                )
            }
        }
        item {
            // With wipe angle animation
            // scaling the full circle degree to alter the full circle wipe angle perception
            CompositionLocalProvider(
                LocalFullCircleDegree provides valueAnimator * 360f,
            ) {
                PieChart(
                    modifier = Modifier.size(200.dp),
                    items = items
                )
            }
        }
        item {
            // With thickness animation
            CompositionLocalProvider(
                LocalPieThicknessPercent provides valueAnimator
            ) {
                PieChart(
                    modifier = Modifier.size(200.dp),
                    items = items
                )
            }
        }
        item {
            // With All Animation
            CompositionLocalProvider(
                LocalStartAngleDegree provides (valueAnimator * 360f) - 90f,
                LocalFullCircleDegree provides valueAnimator * 360f,
                LocalPieThicknessPercent provides valueAnimator
            ) {
                PieChart(
                    modifier = Modifier.size(200.dp),
                    items = items
                )
            }
        }
    }
}