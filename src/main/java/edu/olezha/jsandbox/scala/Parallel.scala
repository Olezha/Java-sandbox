package edu.olezha.jsandbox.scala

import scala.actors._

object Parallel {

  def main(args: Array[String]): Unit = {
    val myActor = new MyActor
    myActor.start()

    for (i <- 1 to 10)
      myActor ! "Hello" + ("!" * i)

    Thread.sleep(1000L)
    myActor.alive = false
    myActor ! "fin"
  }
}

class MyActor extends Actor {

  var alive = true
  
  override def act(): Unit = {
    while (alive) {
      receive {
        case incoming => println("I got mail: "+ incoming)
      }
    }
  }
}
