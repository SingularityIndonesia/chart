# Example of a Default Line Chart
``` kotlin
@Composable
fun LineChartPage(
    items: List<ChartItem>
) {
    LineChart(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
            .then(modifier),
        items = items
    )
}
```
### Result:
<img width="300" alt="Screenshot 2025-03-31 at 05 10 13" src="https://github.com/user-attachments/assets/152d1e3f-3815-4e43-af53-f2c6a2047d30" />

# Example Modifying Line Chart
``` kotlin
@Composable
fun LineChartPage(
    items: List<ChartItem>
) {
    // filter line color into red
    val blueLineFilter = remember {
        ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0f, 0f, 0f, 0f, 0f,  // Red channel -> 0
                    0f, 0f, 0f, 0f, 0f,  // Green channel -> 0
                    1f, 1f, 1f, 1f, 1f,  // Blue channel -> 1f
                    0f, 0f, 0f, 1f, 0f   // Alpha channel remains unchanged
                )
            )
        )
    }

    CompositionLocalProvider(
        LocalColorFilter provides blueLineFilter,
        LocalLineWidth provides 10.dp
    ) {
        LineChart(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .border(BorderStroke(1.dp, Color.Black))
                .padding(8.dp)
                .then(modifier),
            items = items
        )
    }
}
```

### Result
<img width="300" alt="Screenshot 2025-03-31 at 05 14 21" src="https://github.com/user-attachments/assets/c088d1a1-f268-4dd2-9169-6a52304f8df1" />
