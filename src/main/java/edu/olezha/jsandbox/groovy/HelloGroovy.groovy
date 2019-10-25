package edu.olezha.jsandbox.groovy

import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder

println("It's Groovy, baby, yeah!")
println(3 + 0.2)

//String hello = "Hello!"
hello = "Hello!"
String checkHello() {
    print hello.toString() + " "
    "all ok"
}
println checkHello()

list = ["A", "B", "C", "D"]
println list.class.toString() + " " + list.size()

map = [a:"A", b:"B", c:"C"]
println map.getClass()

pc = new Character(strength:10, wisdom:15)
pc.strength = 18
println "STR [" + pc.strength + "] WIS [" + pc.wisdom + "]"

class Character {

    int strength
    int wisdom

    Character() {
        println "construct"
    }

    void setStrength(int strength) {
        println "setStrength($strength)"
        this.strength = strength
    }
}

people = [null, new Person(name:'Gweneth', age: 34), new Person(name:'Oleh')]
for (Person person: people) {
    println "- ${person?.name} ${person?.age}"
}

class Person {

    String name
    int age
}

println """1
2"""
