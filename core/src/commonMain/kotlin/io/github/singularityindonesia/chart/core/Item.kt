package io.github.singularityindonesia.chart.core

import androidx.compose.ui.graphics.Color

data class Item(
    val id: String,
    val value: String,
    val legend: Legend
)

data class Legend(
    val label: String,
    val color: Color
)