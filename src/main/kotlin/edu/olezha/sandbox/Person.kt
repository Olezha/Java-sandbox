package edu.olezha.sandbox

class Person {
    var name = "oleh"
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }

    val rolledValue
        get() = (1..6).shuffled().first()
}
