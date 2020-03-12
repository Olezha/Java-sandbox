package edu.olezha.sandbox

import kotlinx.coroutines.*

fun main() {
    println("Start")
    GlobalScope.launch {
        delay(1_000)
        println("Inside")
    }
    println("End")

    Thread.sleep(3_000)
}
