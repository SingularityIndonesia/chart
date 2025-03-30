# Example of a Pie Chart
``` kotlin
@Composable
fun PieChartPage(
    items: List<ChartItem>
) {
    PieChart(
        modifier = Modifier
            .size(400.dp)
            .padding(16.dp),
        items = items
    )
}
```
### Result:
<img width="300" alt="Screenshot 2025-03-30 at 18 28 42" src="https://github.com/user-attachments/assets/317c4baa-d751-48e2-b952-dff1fefba5d1" />

# Example of an Animated Pie Chart
``` kotlin
@Composable
fun PieChartPage(
    items: List<ChartItem>
) {
    var animate by remember { mutableStateOf(false) }

    val valueAnimator by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        animationSpec = tween(2000)
    )

    LaunchedEffect(Unit) {
        delay(3000)
        animate = true
    }

    CompositionLocalProvider(
        LocalStartAngleDegree provides (valueAnimator * 360f) - 90f,
        LocalFullCircleDegree provides valueAnimator * 360f,
        LocalPieThicknessPercent provides valueAnimator
    ) {
        PieChart(
            modifier = Modifier
                .size(400.dp)
                .padding(16.dp),
            items = items
        )
    }
}
```

### Result:
<img src="https://github.com/user-attachments/assets/a5dcc0b5-753c-4dc9-ae87-c99cd38b661e" width="300">
