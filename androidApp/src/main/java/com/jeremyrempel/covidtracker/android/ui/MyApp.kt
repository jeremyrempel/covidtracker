package com.jeremyrempel.covidtracker.android.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composetest.R
import com.jeremyrempel.covidtracker.api.ApiResult
import com.jeremyrempel.covidtracker.ui.Lce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.toJavaInstant
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

val green = Color(50, 150, 50)
val red = Color(200, 50, 50)

@Composable
fun MyApp(flow: Flow<Lce<ApiResult>>) {

    val uiModel: Lce<ApiResult> by flow.collectAsState(initial = Lce.Loading())

    when (uiModel) {
        is Lce.Loading -> LoadingView()
        is Lce.Error -> ErrorView((uiModel as Lce.Error<ApiResult>).msg) // todo why doesn't smart cast work?
        is Lce.Content<ApiResult> -> MainScreen((uiModel as Lce.Content<ApiResult>).data)
    }
}

@Composable
fun MainScreen(uiModel: ApiResult) {
    var openDialog by remember { mutableStateOf(false) }
    var currentContent by remember { mutableStateOf(0) }

    if (openDialog) {
        SettingsDialog(onDismiss = { openDialog = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(ContextAmbient.current.getString(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { openDialog = true }) {
                        Icon(Icons.Filled.Settings)
                    }
                }
            )
        },
//        bottomBar = {
//            BottomNavigationAlwaysShowLabelComponent(currentContent.value) {
//                currentContent.value = it
//            }
//        },
        bodyContent = {
            when (currentContent) {
                0 -> DataTable(uiModel)
                1 -> Text("States")
                2 -> Text("Settings")
            }
        }
    )
}

@Preview
@Composable
fun SettingsDialogPreview() {
    SettingsDialog(onDismiss = {})
}

@Composable
fun SettingsDialog(onDismiss: () -> Unit) {
    val ctx = ContextAmbient.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(ctx.getString(R.string.about_app_name)) },
        text = {
            Text("Covid Tracker is a free OSS app")
            Text("Data is provided by Covid Tracking Project")
            Text("Source code available on Github")
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(ctx.getString(R.string.button_close))
            }
        }
    )
}

@Composable
fun ErrorView(text: String) {
    Text(text)
}

@Composable
fun LoadingView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Covid Tracker") }
            )
        },
        bodyContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    )
}

@Composable
fun DataTable(
    uiModel: ApiResult
) {
    val decimalFormatter = DecimalFormat("###.##%")
    val numFormatter = DecimalFormat("#,###")
    val dateFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.FULL)
        .withLocale(Locale.US)
        .withZone(ZoneId.of("UTC"))

    Column {
        Box(Modifier.padding(20.dp)) {
            TwoColumnRow("Total Cases", numFormatter.format(uiModel.positive))
            TwoColumnRow(
                "New Daily Cases",
                numFormatter.format(uiModel.positiveIncrease),
                UpDown.UP
            )
            TwoColumnRow("Total Deaths", numFormatter.format(uiModel.death))
            TwoColumnRow("New Daily Deaths", numFormatter.format(uiModel.deathIncrease), UpDown.UP)
            TwoColumnRow("Total Recovered", numFormatter.format(uiModel.recovered))

            TwoColumnRow(
                "Death Rate",
                decimalFormatter.format(
                    uiModel.death.toBigDecimal()
                        .divide(uiModel.positive.toBigDecimal(), 5, RoundingMode.HALF_UP)
                ),
                UpDown.DOWN
            )
            TwoColumnRow(
                "Currently Hospitalized",
                numFormatter.format(uiModel.hospitalizedCurrently),
                UpDown.DOWN
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    "Last Updated: ${dateFormatter.format(uiModel.lastModified.toJavaInstant())}",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BottomNavigationAlwaysShowLabelComponent(selectedIndex: Int, onSelect: (Int) -> Unit) {
    data class BottomNavItems(val id: Int, val title: String, val icon: VectorAsset)

    val listItems = listOf(
        BottomNavItems(0, "US", Icons.Filled.Home),
        BottomNavItems(1, "States", Icons.Filled.List),
        BottomNavItems(2, "Settings", Icons.Filled.Settings)
    )

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
//    var selectedIndex by state { 0 }
    // BottomNavigation is a component placed at the bottom of the screen that represents primary
    // destinations in your application.
    BottomNavigation(modifier = Modifier.fillMaxWidth()) {
        listItems.forEach { item ->
            // A composable typically used as part of BottomNavigation. Since BottomNavigation
            // is usually used to represent primary destinations in your application,
            // BottomNavigationItem represents a singular primary destination in your application.

            BottomNavigationItem(
                icon = { Icon(asset = item.icon) },
                label = { Text(text = item.title) },
                selected = selectedIndex == item.id,
                onSelect = { onSelect(item.id) }
            )
        }
    }
}

enum class UpDown { UP, DOWN, NEUTRAL }

@Composable
fun TwoColumnRow(left: String, right: String, upDown: UpDown = UpDown.NEUTRAL) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(left)

        Row(
            verticalGravity = Alignment.CenterVertically
        ) {
            Text(right, color = Color.Gray, modifier = Modifier.padding(end = 6.dp))

            when (upDown) {
                UpDown.UP -> {
                    Image(
                        asset = vectorResource(id = R.drawable.ic_baseline_arrow_drop_up_24),
                        colorFilter = ColorFilter.tint(red)
                    )
                }
                UpDown.DOWN -> {
                    Image(
                        asset = vectorResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                        colorFilter = ColorFilter.tint(green),
                    )
                }
                else -> {
                    Box(modifier = Modifier.width(24.dp))
                }
            }
        }
    }
    Divider(color = Color.Gray, modifier = Modifier.padding(vertical = 10.dp))
}

@Preview
@Composable
fun DefaultPreview() {
    val data = flowOf(
        Lce.Content(
            ApiResult(
                20201020,
                "201212",
                100000,
                234,
                "abc123",
                200000,
                100000,
                100000,
                200,
                100,
                10000,
                "20201001",
                1000,
                100,
                1000,
                100,
                5000,
                1000,
                5000,
                1000,
                6000,
                50,
                700000,
                1000000,
                10000,
            )
        )
    )

    MyAppTheme(false) {
        MyApp(data)
    }
}
