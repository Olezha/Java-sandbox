package edu.olezha.sandbox

import kotlinx.coroutines.*

fun main() {
    val j = GlobalScope.launch {
        val a1 = fetchAsync(3)
        val a2 = fetchAsync(4)
        val a3 = fetchAsync(7)
        a1.await().out()
        a2.await().out()
        a3.await().out()
    }

    GlobalScope.launch {
        repeat(10) {
            delay(400)
            "Launch $it".out()
        }
    }

    repeat(5) {
        Thread.sleep(1_000)
        "Main $it".out()
    }
}

fun fetchAsync(sec: Long): Deferred<String> {
    return GlobalScope.async {
        delay(sec * 1_000)
        "Async ok $sec"
    }
}
