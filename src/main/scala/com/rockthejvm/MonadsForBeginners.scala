package com.rockthejvm

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global // global execution context

object MonadsForBeginners extends App {

    //Creating our own monad
    case class SafeClass[+T](private val internalValue: T) {
        def get: T = synchronized { // synchronized block that returns internalValue
            internalValue
        }
        def flatMap[S]( transformer: T => SafeClass[S]): SafeClass[S] = synchronized {
            transformer(internalValue)
        }
    } // consider SafeClass as a wrapper that allows for multi threaded access

    def giveMeSafeValue[T]( value: T): SafeClass[T] = SafeClass(value)

    val safeString: SafeClass[String] = giveMeSafeValue("Safe string")

    val string = safeString.get // extract
    val upperCaseString = string.toUpperCase //transform
    val upperSafeString = giveMeSafeValue(upperCaseString) //wrap

    val upperSafeString2 = safeString.flatMap(x=> SafeClass(x.toUpperCase()))

    //Option
    case class Person(firstName: String, lastName: String)

    def getPerson(firstName: String, lastName: String) : Option[Person] = {
        Option(firstName).flatMap(firstName => Option(lastName).flatMap(lastName => {
            Option(Person(firstName, lastName))
        }))
    }

    println(getPerson("kenneth", "Sim"))
    println(getPerson(null, "sim"))

    def getPersonForComprehension(firstName: String, lastName: String): Option[Person] = {
        for {
            firstName <- Option(firstName)
            lastName <- Option(lastName)
        } yield Person(firstName, lastName)
    }

    println(getPersonForComprehension("kenneth", "Sim"))
    println(getPersonForComprehension(null, "sim"))


    //Futures
    println("Starting Futures")
    case class User(id: String)
    case class Product(sku: String, price: Double)

    def getUser(url: String) : Future[User] = {
        Future {
            User("Kenneth")
        }
    }

    def getLastProduct(userId: String) : Future[Product] = Future {
        Product("12312-213", 99.99)
    }

    val kennethUrl = "www.kenneth.com"
    val product: Future[Double] = getUser(kennethUrl)
            .flatMap(user => getLastProduct(user.id))
            .map(_.price * 1.19)

    val productForComprehension = for {
        user <- getUser(kennethUrl)
        product <- getLastProduct(user.id)
    } yield (product.price * 1.19)

    println(productForComprehension)

    //for comprehension
    val chars = List('a','b', 'c')
    val nums = List(1,2,3)
    val tupleList: List[(Int, Char)] = for {
        num <- nums
        char <- chars
    } yield (num, char)
    println(tupleList)
}

