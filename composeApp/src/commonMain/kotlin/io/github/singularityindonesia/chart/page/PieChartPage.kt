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
import androidx.compose.ui.unit.dp
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