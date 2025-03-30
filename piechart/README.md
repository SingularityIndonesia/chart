# Example of a Pie Chart
``` kotlin
@Composable
fun PieChartPage(
    items: List<ChartItem>
) {
    CompositionLocalProvider(
        LocalStartAngleDegree provides -90f,
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

# Example of a Color-Filtered Animated Pie Chart
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

    // filter color into gray scale
    val grayScaleFilter = remember {
        ColorFilter.colorMatrix(
            ColorMatrix().apply {
                setToSaturation(0f)
            }
        )
    }
    CompositionLocalProvider(
        LocalStartAngleDegree provides (valueAnimator * 360f) - 90f,
        LocalFullCircleDegree provides valueAnimator * 360f,
        LocalPieThicknessPercent provides valueAnimator,
        LocalColorFilter provides grayScaleFilter
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
<img src="https://github.com/user-attachments/assets/99eb961e-0107-4a3f-a879-dbb94c8c7a2e" width="300">

