package com.example.assign4_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    private val _autoIncrement = MutableStateFlow(false)
    val autoIncrement: StateFlow<Boolean> = _autoIncrement

    private val _simpleFlowRepeatRate = MutableStateFlow(3)
    val simpleFlowRepeatRate: StateFlow<Int> = _simpleFlowRepeatRate

    private var autoIncrementJob: Job? = null

    fun setSimpleFlowRepeatRate(rate: Int) {
        _simpleFlowRepeatRate.value = rate
        // if auto-increment is active, restart it with the new rate
        if (_autoIncrement.value) {
            startAutoIncrement()
        }
    }

    fun setAutoIncrement(flag: Boolean) {
        _autoIncrement.value = flag
        if (flag) {
            startAutoIncrement()
        } else {
            stopAutoIncrement()
        }
    }

    private fun startAutoIncrement() {
        autoIncrementJob?.cancel()
        autoIncrementJob = viewModelScope.launch {
            while (true) {
                delay(_simpleFlowRepeatRate.value * 1000L)
                incrementCounter(true)
            }
        }
    }

    private fun stopAutoIncrement() {
        autoIncrementJob?.cancel()
        autoIncrementJob = null
    }

    fun incrementCounter(positiveIncrease: Boolean) {
        if (positiveIncrease) {
            _counter.value++
        } else {
            _counter.value--
        }
    }

    fun resetCounter() {
        _counter.value = 0
    }
}
