package edu.olezha.sandbox

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        repeat(10) {
            delay(400)
            "Async $it".out()
        }
    }

    repeat(5) {
        Thread.sleep(1_000)
        "Main $it".out()
    }
}
