package io.github.singularityindonesia.chart

sealed interface Page {
    val pageTitle: String

    data object PieChart : Page {
        override val pageTitle: String = "Pie Chart"
    }

    data object LineChart : Page {
        override val pageTitle: String = "Line Chart"
    }
}