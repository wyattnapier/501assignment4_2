package com.example.assign4_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assign4_2.ui.theme.Assign4_2Theme

class MainActivity : ComponentActivity() {
    val vm: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assign4_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), // Make the surface fill the entire screen
                    color = MaterialTheme.colorScheme.background // Use background color from the theme
                ) {
                    // Display the main screen composable
                    MainScreen(vm)
                }
            }
        }
    }
}

@Composable
fun MainScreen(vm: MainViewModel) {
    Column {
        Text(text = "Counter: ${vm.getCounter()}")
        Row{
            Button(onClick = { vm.incrementCounter(false) }) {
                Text(text = "Decrement")
            }
            Button(onClick = { vm.incrementCounter(true) }) {
                Text(text = "Increment")
            }
        }
        Button(onClick = { vm.resetCounter() }) {
            Text(text = "Reset")
        }
        Button(onClick = { /* open the settings composable and close this one somehow? */ }) {
            Text(text = "Settings")
        }
    }
}

@Composable
fun Settings(vm: MainViewModel) {
    Slider(
        value = vm.getSimpleFlowRepeatRate().toFloat(),
        onValueChange = { vm.setSimpleFlowRepeatRate(it.toInt()) },
        valueRange = 1f..10f)
    Switch(checked = vm.getAutoIncrement(), onCheckedChange = { vm.setAutoIncrement(it) })
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    Assign4_2Theme {
        MainScreen(vm = MainViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    Assign4_2Theme {
        Settings(vm = MainViewModel())
    }
}