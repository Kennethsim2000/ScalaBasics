package com.rockthejvm

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global // equivalent to a thread pool, that we can schedule tasks

object Advanced extends App {
    //lazy evaluation, not evaluated until it is used
    lazy val lazyVal = 2

    lazy val lazyExp = {
        println("I am so lazy")
        43
    } // this exp is not evaluated until it is used

    val eager = lazyExp + 1 // here, because we use lazyExp, it will be evaluated and he print statement will be called

    //"pseudo collections": Option, Try
    def methodWhichCanReturnNull(): String = "I am learning Scala"
    val anOption = Option(methodWhichCanReturnNull()) //Some("I am learnign Scala)
    //option = "collection" with at most one element. Some(val) or None

    val stringProcessing = anOption match {
        case  Some(validString) => println(s"I obtained a valid string $validString")
        case None => println("Obtained null")
    }

    def methodWhichCanThrowException(): String = throw new RuntimeException()
    val tryObject = Try(methodWhichCanThrowException()) //Contains a try object containing a string if the method went
    // correctly or it contains the exception that is thrown

    val anotherStringProcessing = tryObject match {
        case Success(value) => println(s"I have obtained a value $value")
        case Failure(exception) => println(s"I have obtained a exception $exception")
    }

    /**
     * Evaluate something on another thread
     * Asynchronous programming
     */
    val aFuture = Future({
        println("Future executing")
        Thread.sleep(1000)
        78
    })
    //Future is a collection which contains a value when it is evaluated.
    //Future is composable with map, flatmap and filter

    /**
     * Implicits basics
     */
    def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
    implicit val implicitNum:Int = 43
    val res = aMethodWithImplicitArgs // aMethodWithImplicitArgs(implicitNum)
    println(res)

    /**
     * Implicit conversion
     */
    implicit class MyRichInteger(num:Int) {
        def isEven():Boolean = num % 2 == 0
    }

    println(23.isEven())

}
