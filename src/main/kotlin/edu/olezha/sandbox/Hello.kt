package edu.olezha.sandbox

import kotlin.random.Random

const val SOME_MAX = 5_000

fun main() {
    println("Hello, world!")

    val x = 5
    var y: Long = 2
    y *= x

    if (Random.nextInt(0, 100) % 2 == 0)
        y += SOME_MAX

    if (y <= SOME_MAX)
        println(y)
    else
        System.err.println("overflow")

    val playerName = "Estragon"
    println(playerName.reversed())

    val b = if (Random.nextBoolean()) "T"  else "F"
    println(b)

    val z = Random.nextInt(0, 100)
    if (z in 25..75)
        println("$z in 25..75")
    else
        println("$z out 25..75")

    val msg = s(z)
    println(msg)

    f()
}

private fun s(z: Int) =
    when (z) {
        in 0..25 -> "in 0..25"
        in 26..50 -> "in 26..50"
        else -> "greater then 50 (+${z - 50})"
    }

private fun f(top: Int = 3) {
    println((1..top).toList())
    println(0 in top downTo 1)
    println(1 in 1 until top)
}
