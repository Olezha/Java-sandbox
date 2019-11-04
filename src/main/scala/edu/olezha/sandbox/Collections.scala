package edu.olezha.sandbox

import java.util

object Collections {

  def main(args: Array[String]): Unit = {
    var list = List(1, 2, 3)
    list = list:+ 4
    println(list)

    val jlist = new util.ArrayList[String]()
    jlist.add("foo")
    jlist.add("bar")
    println(jlist)

    val x = 1 :: 2 :: Nil
    println(x)
    println(3 :: x)

    var map = Map(1 -> "hi", 2 -> "there")
    for ((k, v) <- map)
      println(k + ": " + v)
    map = map + (3 -> "bye")
    println(map)

    val y = 3 :: 4 :: Nil
    val xy = x ::: y
    println(xy)

    class Pet(name: String)
    class Cat(name: String) extends Pet(name: String)
    class Dog(name: String) extends Pet(name: String)
    class BengalKitten(name: String) extends Cat(name: String)
    case class Queue[T](e: T*) {
      var elems: List[T] = List[T](e: _*)

      def enqueue(e: T): List[T] = elems ::: List(e)

      def dequeue: T = {
        val result = elems.head
        elems = elems.tail
        result
      }
    }

    val q = Queue[Pet]()
    q.enqueue(new Cat("cat"))
    q.enqueue(new Dog("dog"))
    println(q)
  }
}
