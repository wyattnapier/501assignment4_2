package com.example.assign4_2

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel {
    private var simpleFlowRepeatRate: Int = 3
    private var autoIncrement: Boolean = true
    private var counter: Int = 0

    // setter method for simpleFlowRepeatRate
    public fun setSimpleFlowRepeatRate(rate: Int) {
        simpleFlowRepeatRate = rate;
    }

    public fun getSimpleFlowRepeatRate(): Int {
        return simpleFlowRepeatRate;
    }

    // setter method for autoIncrement flag
    public fun setAutoIncrement(flag: Boolean) {
        autoIncrement = flag;
    }

    // getter method for autoIncrement flag
    public fun getAutoIncrement(): Boolean {
        return autoIncrement;
    }

    // setter method for counter (given positive or negative 1)
    public fun incrementCounter(positiveIncrease: Boolean) {
        var rate = 1
        if (!positiveIncrease) {
           rate = -1
        }
        counter += rate;
    }

    // setter method for counter
    public fun resetCounter() {
        counter = 0;
    }

    // getter method for counter
    public fun getCounter(): Int {
        return counter;
    }

    // simple flow to autoincrement
    val simpleFlow: Flow<Int> = flow {
        while (autoIncrement) {
            incrementCounter(true)
            emit(getCounter())
            delay(getSimpleFlowRepeatRate() * 1000L)
        }
    }
}