package edu.olezha.sandbox

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        measureNanoTime {
            val values = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
            values
                    .filter { v -> v >= 5 }.out()
                    .windowed(2, 2).out()
                    .map { v -> v[0] * v[1] }.out()
                    .fold(0) { acc, v -> acc + v }.out()
        }.out()
    }.out()
}
