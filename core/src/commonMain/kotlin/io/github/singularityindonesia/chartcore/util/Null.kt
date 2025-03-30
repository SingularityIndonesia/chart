package io.github.singularityindonesia.chartcore.util

fun <T> T?.ifNull(fallback: () -> T): T {
    return this ?: fallback()
}