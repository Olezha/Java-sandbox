package edu.olezha.sandbox

import java.io.File
import java.io.FileNotFoundException
import java.lang.Error
import java.lang.Exception
import kotlin.random.Random

const val SOME_MAX = 5_000
const val NAME = "Oleh Sh"

fun main() {
    println("Hello, world!")

    var x = 5
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

    var z = Random.nextInt(0, 100)
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
    println(fn(NAME.substring(NAME.indexOf(" ") until NAME.length)))
    println(fn(NAME.substring(0 until NAME.indexOf(" "))))

    var nullable: String? = "nullable"
    nullable = null
    println(nullable)

//    print("readline ")
//    var line = readLine()?.capitalize()
    var line: String? = "ABC"
    println(line)
    line = line?.let {
        if (it.isNotBlank())
            it.decapitalize()
        else
            "Blank"
    }
    println(line)
//    line = readLine()!!.capitalize()
    println(line)
//    line = readLine() ?: throw SomeException()
    line = line?.capitalize()
    println(line)

    if (Random.nextBoolean())
        line = null
    try {
        checkNotNull(line, { "message" })
    } catch (e: IllegalStateException) {
        println(e)
    }
//    require(line.length > 10)
    // requireNotNull error assert

//    throw Exception()

    println(NAME.split(" "))

    val typeNamePrice = "shandy,Dragon's Breath,5.91"
    val (type, name, price) = typeNamePrice.split(",")
    println("$name - $type - $price")

    println(toDragonSpeak(NAME))
    println(toDragonSpeak("Olezha"))

    val ca = "qwerty".toCharArray();
    val s1 = String(ca)
    val s2 = String(ca)
    println("$s1 == $s2: " + (s1 == s2))
    println("$s1 === $s2: " + (s1 === s2))

    val om = '\u0950'
    println(om)
    NAME.forEach { when (it) {
        ' ' -> {}
        else -> println("$it")
    }}

    val gold = "5.91".toIntOrNull() ?: 0
    println(gold)

    var menuFile = File("menu-file.txt").apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }

    val firstItemSquared = listOf(3,2,1).first().let {
        it * it
    }
    println(firstItemSquared)

    try {
        menuFile = File("menu-file.txt")
        val servesDragonsBreath = menuFile.run {
            readText().contains("Dragon's Breath")
        }
    } catch (e: FileNotFoundException) {
        println(e)
    }

    fun nameIsLong(name: String) = name.length >= 20
    "Madrigal"
            .run(::nameIsLong)
            .run(::println)
    "Polarcubis, Supreme Master of NyetHack"
            .run(::nameIsLong)
            .run(::println)

    val healthPoints = 1
    val status = run {
        if (healthPoints == 100) "perfect health" else "has injuries"
    }
    println(status)

    val nameTooLong = with("Polarcubis, Supreme Master of NyetHack") {
        length >= 20
    }
    println(nameTooLong)

    "abc"
            .also { println(it) }
            .also { println(it) }

    var fileContents = File("myfile.txt")
            .takeIf { it.canRead() && it.canWrite() }
            ?.readText()
    println(fileContents)

    fileContents = File("myfile.txt")
            .takeUnless { !it.canRead() }
            ?.readText()
    println(fileContents)

    var list: List<String> = listOf("one", "two", "three")
    println(list)
    println(list[2])
    println(list.getOrElse(3) {"unknown"})
    println(list.getOrNull(4) ?: "unknown")
    list = list.toMutableList()
    list.add("four")
    println(list)
    list.forEachIndexed { i, v ->
        println("$i $v")
    }

    list = File("data/data.txt")
            .readText()
            .split("\n")
    println(list)
    println(list.shuffled())

    (list as MutableList)[0] = "0"
    println(list)

    val map = mutableMapOf("a" to "A", "b" to "B", "c" to "C")
    map += "c" to "C"
    println(map)
    println(map["c"])
    println(map.getOrDefault("d", "-"))

    val person = Person()
    println(person.name)
    person.name = " Olezha"
    println(person.name)
    println(person.rolledValue)
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

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiou]")) {
            when (it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }
