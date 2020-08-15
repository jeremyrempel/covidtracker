package com.jeremyrempel.covidtracker.android.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composetest.R

val green = Color(50, 150, 50)
val red = Color(200, 50, 50)

@Composable
fun MyApp() {

    val currentContent = state { 0 }
    val openDialog = state { false }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Covid Tracker US") },
                actions = {
                    IconButton(onClick = { openDialog.value = true }) {
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
            when (currentContent.value) {
                0 -> DataTable()
                1 -> Text("States")
                2 -> Text("Settings")
            }
        }
    )
}

@Composable
fun DataTable() {

    Column {
        Box(Modifier.padding(20.dp)) {
            TwoColumnRow("Total Cases", "5,172,216", UpDown.UP)
            TwoColumnRow("New Cases", "55742", UpDown.UP)
            TwoColumnRow("Total Hospitalized", "47,919", UpDown.DOWN)
            TwoColumnRow("Total ICU", "9533", UpDown.UP)
            TwoColumnRow("Total Ventilator", "9533", UpDown.DOWN)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text("Last Updated Aug 12, 2020", color = Color.Gray)
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

            if (upDown == UpDown.UP) {
                Image(
                    asset = vectorResource(id = R.drawable.ic_baseline_arrow_drop_up_24),
                    colorFilter = ColorFilter.tint(green)
                )
            } else if (upDown == UpDown.DOWN) {
                Image(
                    asset = vectorResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                    colorFilter = ColorFilter.tint(red),
                )
            }
        }
    }
    Divider(color = Color.Gray, modifier = Modifier.padding(vertical = 10.dp))
}

@Preview
@Composable
fun DefaultPreview() {
    Text("Hello World")
}