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
package io.github.singularityindonesia.chartcore.compose

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalStartAngleDegree = compositionLocalOf { -90f }
val LocalFullCircleDegree = compositionLocalOf { 360f }
val LocalPieThicknessPercent = compositionLocalOf { .5f }
val LocalColorFilter = compositionLocalOf<ColorFilter?> { null }
val LocalLineWidth = compositionLocalOf<Dp> { 2.dp }