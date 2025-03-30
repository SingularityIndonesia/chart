package io.github.singularityindonesia.chartcore.util

fun <T> List<T>.secondOrNull(): T? = runCatching { this[1] }.getOrNull()