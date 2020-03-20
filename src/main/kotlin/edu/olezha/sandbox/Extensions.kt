package edu.olezha.sandbox

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.out(): T {
    println(this)
    return this
}

fun main() {
    "Hi".out().addEnthusiasm(5).out()
    36.out().out()
}
