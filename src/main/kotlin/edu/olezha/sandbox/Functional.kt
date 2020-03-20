package edu.olezha.sandbox

fun main() {
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    animals.out()
            .map { animal -> "A baby $animal" }.out()
            .map { baby -> "$baby, with the cutest little tail ever!" }.out()
    animals.out()

    listOf(listOf(1, 2, 3), listOf(4, 5)).out()
            .flatMap { it }.out()

    val itemsOfManyColors = listOf(
            listOf("red apple", "green apple", "blue apple"),
            listOf("red fish", "blue fish"),
            listOf("yellow banana", "teal banana")
    ).out()
    val redItems = itemsOfManyColors.flatMap { it.filter { it.contains("red") } }.out()

    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11).out()
    val primes = numbers.filter { number ->
        (2 until number).map { number % it }
                .none { it == 0 }
    }.out()

    val employees = listOf("Denny", "Claudette", "Peter")
    val shirtSize = listOf("large", "x-large", "medium")
    employees
            .zip(shirtSize).out()
            .toMap().out()

    val foldedValue =listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
        print("Accumulated value: $accumulator + ${number}x3 = ")
        (accumulator + (number * 3)).out()
    }
    "Final value: $foldedValue".out()
}
