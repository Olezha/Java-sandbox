package edu.olezha.sandbox

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun Any.out() = println(this)

fun main() {
    "Hi".addEnthusiasm(5).out()
    36.out()
}
