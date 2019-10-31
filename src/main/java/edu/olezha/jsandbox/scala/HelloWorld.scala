package edu.olezha.jsandbox.scala

import edu.olezha.jsandbox.core.FizzBuzz

object HelloWorld {

  private val hw = HelloWorld

  def main(args: Array[String]) {
    val hello = "Hello World!"
    println(hello.+("!!"))

    println(hw.toString)
    println("len: " + len(hw))

    val dog = new Dog("Ches")
    dog voice()
    print("fact 5: ".+(dog fact 5))

    FizzBuzz.main(null)

    var counter = 1
    while (counter <= 10) {
      println(".".*(counter))
      counter = counter + 1
    }

    for (i <- 1 to 10) println(i)

    for (i <- 1 to 10; if i % 2 == 0) println(i)

    for (x <- 1 to 5; y <- 1 to x)
      println(" " * (x - y) + x.toString * y)

    val xs = for (x <- 2 to 11) yield dog.fact(x)
    println(xs)
    for (factx <- xs) println(factx)

    val doubler = (x : Int) => { 2 * x }
    println(doubler(4))

    val adder = (n : Int) => { x : Int => x + n }
    val plus2 = adder(2)
    val plus3 = adder(3)
    println(plus2(3) + " " + plus3(3))
    println(plus2(4) + " " + plus3(4))

    val dog2 = new Dog("Field")
    println(dog2)

    val cashFlow = new CashFlow("UAH")
    cashFlow.print()

    println(Retriever(null))

    case class Point(x : Int, y : Int) {
      def *(m : Int) = Point(this.x * m, this.y * m)
      def +(other : Point) = Point(this.x + other.x, this.y + other.y)
    }
    val xaxis = Point(2, 0)
    val yaxis = Point(0, 3)
    val some = Point(5, 12)
    val whereami = (p : Point) => p match {
      case Point(x, 0) => "On the x-axis"
      case Point(0, y) => "On the y-axis"
      case _ => "Out in the plane"
    }
    println(whereami(xaxis))
    println(whereami(yaxis))
    println(whereami(some))
    println("x; " + xaxis + "; y: " + yaxis)
    println("x * 2: " + xaxis * 2)
    println("x + y: " + (xaxis + yaxis))
  }

  private def len(obj: AnyRef) = {
    obj.toString.length
  }
}

class Dog(name: String) {

  def voice(): Unit = {
    println("woof")
  }

  def fact(base: Int): Int = {
    if (base <= 0)
      1
    else
      base * fact(base - 1)
  }

  override def toString: String = {
    name
  }
}

class CashFlow private (amt : Double, curr : String) {

  def amount: Double = amt
  def currency: String = curr

  def this(amt: Double) = this(amt, "USD")
  def this(curr: String) = this(0, curr)

  def print(): Unit = {
    println(amount.+(" ").+(currency))
  }
}

trait Chipped {

  var chipName: String

  def getName: String = chipName
}

case class Retriever(name: String) extends Dog(name: String) with Chipped {
  override var chipName: String = name
}
