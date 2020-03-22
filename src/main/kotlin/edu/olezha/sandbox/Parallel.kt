package edu.olezha.sandbox

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch {
        fetchAsync(3).await().out()
        fetchAsync(1).await().out()
        fetchAsync(3).await().out()
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
