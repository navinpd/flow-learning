package com.big.flowlearning

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    lateinit var countDownFlow : Flow<Int>
    init {

        //Cold Flow, it doesn't do anything if there are no subscribers
        countDownFlow = flow {
            var timeNow = 10000
            emit(timeNow)

            while (timeNow > 0) {
                delay(1000)
                timeNow -= 1000
                emit(timeNow)
            }
        }
        collectFlow()
    }


    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect {
                Log.d("HELLO", it.toString())
            }
        }
    }
}