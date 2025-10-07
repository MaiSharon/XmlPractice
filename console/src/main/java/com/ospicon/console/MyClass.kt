package com.ospicon.console

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    runBlocking {
        val job1 = launch(Dispatchers.IO) {
            println("協程start  Dispatchers.IO")
            yield()
            delay(5000)
            println("協程end  Dispatchers.IO")
        }

        val job2 = launch(Dispatchers.Unconfined) {
            println("協程start  Dispatchers.Unconfined")
            delay(5000)
            println("協程end  Dispatchers.Unconfined")
        }

//        job.join()
        println("job end")
//        job1.join()
    }

    println("end")
}