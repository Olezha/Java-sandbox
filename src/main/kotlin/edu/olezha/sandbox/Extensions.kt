package edu.olezha.sandbox

infix fun String.addEnthusiasm(amount: Int) = this + "!".repeat(amount)

val String.numVowels
    get() = count { "aeiouy".contains(it) }

fun <T> T.out(): T {
    println(this)
    return this
}

infix fun String?.printWithDefault(default: String) = println(this ?: default)

fun main() {
    "Hi".out()
            .addEnthusiasm(5).out()
            .numVowels.out()
    36.out().out()

    val nullableString: String? = null
    nullableString printWithDefault "Default string"

    println("Hi" addEnthusiasm 3)
}
