package com.example.assign4_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assign4_2.ui.theme.Assign4_2Theme

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assign4_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isSettingsScreen by remember { mutableStateOf(false) }
                    if (isSettingsScreen) {
                        SettingsScreen(vm) { isSettingsScreen = false }
                    } else {
                        MainScreen(vm) { isSettingsScreen = true }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(vm: MainViewModel, onSettingsClick: () -> Unit) {
    val counter by vm.counter.collectAsState()
    val autoIncrement by vm.autoIncrement.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: $counter")
        Text(text = "Auto mode: ${if (autoIncrement) "ON" else "OFF"}")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { vm.incrementCounter(false) }) {
                Text(text = "-1")
            }
            Button(onClick = { vm.incrementCounter(true) }) {
                Text(text = "+1")
            }
        }
        Button(onClick = { vm.resetCounter() }) {
            Text(text = "Reset")
        }
        Button(onClick = onSettingsClick) {
            Text(text = "Settings")
        }
    }
}

@Composable
fun SettingsScreen(vm: MainViewModel, onBack: () -> Unit) {
    val autoIncrement by vm.autoIncrement.collectAsState()
    val simpleFlowRepeatRate by vm.simpleFlowRepeatRate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Auto-increment")
            Switch(
                checked = autoIncrement,
                onCheckedChange = { vm.setAutoIncrement(it) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Auto-increment interval: $simpleFlowRepeatRate seconds")
        Slider(
            value = simpleFlowRepeatRate.toFloat(),
            onValueChange = { vm.setSimpleFlowRepeatRate(it.toInt()) },
            valueRange = 1f..10f,
            steps = 9
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}
