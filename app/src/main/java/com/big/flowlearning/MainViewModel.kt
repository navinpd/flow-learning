package com.big.flowlearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    //Cold Flow, it doesn't do anything if there are no subscribers
    var countDownFlow: Flow<Int> = flow {
        var timeNow = 5
        emit(timeNow)

        while (timeNow > 0) {
            delay(1000)
            timeNow -= 1
            emit(timeNow)
        }
    }

    private val _stateFlow = MutableStateFlow(0)
    val statFlow = _stateFlow.asStateFlow()

    init {
        //collectFlow()
        //collectFlatMap()
        // collectionFlowRealUseCase()
        // incrementCounter()
    }

    //State Flow & Shared Flow
    fun incrementCounter() {
        _stateFlow.value += 1
    }

    //Flow use case
    private fun collectionFlowRealUseCase() {
        val flow1 = flow {
            emit("Appetizer")
            delay(500L)
            emit("Main Course")
            delay(200L)
            emit("Dessert")
            delay(100L)
        }
        viewModelScope.launch {
            //sequential
            flow1.onEach {
                println("Flow: Item delivered $it")
            }
//              parallel responce collection
//                .buffer()
//              Go to latest emission instead of completing old
//                .conflate()
//              Only collect the latest and skip current
//                .collectLatest()
                .collect {
                    println("Flow: Now eating $it")
                    delay(500L)
                    println("Flow: Finished eating $it")
                }
        }
    }

//    private fun collectFlatMap() {
//        //Usecase repository local data first from database
//        val flow1 = flow {
//            emit(1)
//            delay(500L)
//            emit(2)
//        }
//        viewModelScope.launch {
//            flow1.flatMapConcat { value ->
//                //The data is collected and re-shared after receiving it from internet
//                flow {
//                    emit(value + 1)
//                    delay(500L)
//                    emit(value + 2)
//                }
//            }.collect { value ->
//                println("The value is $value")
//            }
//        }
//
//        val flow2 = (1..5).asFlow()
//        viewModelScope.launch {
//                //flow2.flatMapLatest {  }
//            flow2.flatMapMerge { id ->
//                //The data is collected and re-shared after receiving it from internet
//                //getResultById(id)
//                (3..5).asFlow()
//            }.collect { value ->
//                println("The value is $value")
//            }
//        }
//    }

    private fun collectFlow() {
        //1. Flow Collection
        viewModelScope.launch {
//            val count = countDownFlow.filter { time ->
//                time % 2000 == 0
//            }.map { time ->
//                time * time / 1000000
//            }.onEach { time ->
//                println(time)
//            }
//                // .collect { time ->
//                //  println("The current time is $time")
//                // }
//                //.count()
//                .count {
//                    it % 2 == 0
//                }
//
//            println("The count is $count")
            //2.1 Terminal Counter: accumulator adds with count : 5,4,3,2,1
//            val reducedValue = countDownFlow.reduce { accumulator, count ->
//                accumulator + count
//            }
//            println("The count is $reducedValue") //15
            //2.2 Terminal Operator: count starts with 100
//            val reducedValue = countDownFlow.fold(100) { accumulator, count ->
//                accumulator + count
//            }
//            println("The count is $reducedValue") //115
        }
        //3. Flow Collection
//        countDownFlow.onEach { time ->
//            print(time)
//        }.map {
//
//        }.launchIn(viewModelScope)
    }
}