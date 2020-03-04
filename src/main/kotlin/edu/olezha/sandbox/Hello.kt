package edu.olezha.sandbox

import java.lang.Error
import java.lang.Exception
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

    a(1, "a")
    a(b = "b", a = 2)

    try {
        aTodo()
    } catch (e: Error) {
        println(e)
    }

    `))`()

    val s = "Mississippi"
    var numLetters = s.count()
    println("$s $numLetters")
    numLetters = s.count { it == 's' }
    println("$s $numLetters")

    println({
        "hi"
    }())

    var counter = 0
    val hi = {
        counter++
        "Hi $counter"
    }
    println(hi())
    println(hi())
    println(hi())

    val hii: (Int, String) -> String = { term, n ->
        counter += term
        "Hii$n $counter"
    }
    println(hii(2, "2"))
    println(hii(3, "3"))

    val hiii: (Int) -> String = {
        counter += it
        "Hiii$it $counter"
    }
    println(hiii(2))
    println(hiii(3))

    val hiiiii = { term: Int, n: String ->
        counter += term
        "Hii$n $counter"
    }
    println(hiiiii(4, "4"))
    println(hiiiii(5, "5"))

    val funFun = { name: String, num: Int ->
        "Welcome $name ($num)"
    }
    funFunction("Oleh", funFun)
    funFunction("Guyal") { name, num ->
        "Hi $name ($num)"
    }

    funFunction("Guyal", ::sis)

    val fn = configureFunction()
    println(fn("Oleh"))

    var nullable: String? = "nullable"
    nullable = null
    println(nullable)

    print("readline ")
    var line = readLine()?.capitalize()
    println(line)
    line = line?.let {
        if (it.isNotBlank())
            it.decapitalize()
        else
            "Blank"
    }
    println(line)
    line = readLine()!!.capitalize()
    println(line)
    line = readLine() ?: throw SomeException()
    line = line.capitalize()
    println(line)

    if (Random.nextBoolean())
        line = null
    checkNotNull(line, {"message"})
//    require(line.length > 10)
    // requireNotNull error assert

//    throw Exception()
}

class SomeException() :
        IllegalStateException()

private fun configureFunction(): (String) -> String {
    return { name: String ->
        println("l $name")
        name
    }
}

private inline fun funFunction(name: String, greetingFunction: (String, Int) -> String) {
    val num = (1..3).shuffled().last() // rand 1, 2 or 3
    println(greetingFunction(name, num))
}

private fun sis(name: String, v: Int): String {
    return "sis: $name $v"
}

private fun `))`() {
    println("))")
}

private fun aTodo(): String {
    TODO("implement the ... to [...][return ...]")
}

private fun a(a: Int, b: String) {
    println("$a $b")
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
