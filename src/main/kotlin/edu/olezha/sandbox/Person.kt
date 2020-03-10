package edu.olezha.sandbox

open class Person(_name: String, val surname: String,
             private var age: Int = 0) {
    var name = _name
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }

    private val rolledValue
        get() = (26..36).shuffled().first()

    var hometown: String? = null

    lateinit var significantOther: Person

    val weight by lazy {
        // some staff
        80
    }

    init {
        require(name.isNotBlank()) { "Person must have a name" }
        if (age == 0) age = rolledValue
    }

    constructor(name: String) : this(name, "")

    constructor(name: String, surname: String) : this(name, surname, 0) {
        age = (26..36).shuffled().first()
    }

    fun howOldAreYou(): Int {
        return age + (-5..5).shuffled().first()
    }

    fun isMaried(): Boolean {
        if (::significantOther.isInitialized)
            return true
        return false
    }
}
