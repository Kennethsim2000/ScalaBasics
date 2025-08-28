package com.rockthejvm

object ScalaPractice extends App {

    // Ex1: create a function that takes in int, returns an int, as well as an int, and
    def evaluate(func : Int=> Int, num: Int): Int = {
        func(num);
    }

    def addTwo = (num: Int) => num + 2;
    def multiplyTwo = (num:Int) => num * 2;
//    println(evaluate(addTwo, 2))
//    println(evaluate(multiplyTwo, 3))

    // Ex2: Write a function called multiply that takes in an element, and returns a function that takes in y,
    // and returns x * y. With this, we can create functions multiplyByTwo, multiplyByThree

    def multiply(num: Int): Int => Int = {
        (x: Int) => num * x
    }

    val multiplyByTwo = multiply(2)
    val multiplyByThree = multiply(3)
//    println(multiplyByTwo(4))
//    println(multiplyByThree(4))

    // Ex3: create two functions, and use the keyword compose to compose the two functions, use and then as well

    val func1 = (num:Int) => num + 4
    val func2 = (num:Int) => num * 2

    val funcComposed = func1 compose func2 // func1(func2(x))
    val funcAndThen = func2 andThen func1
//    println(funcComposed(3))
//    println(funcAndThen(3))

    // Ex4: pattern matching, takes in a string, demarcating operation, and arguments: String

    sealed trait Operation
    case object ListOp extends Operation
    case class GrepOp(arguments:String) extends Operation
    case class RemoveOp(arguments:String) extends Operation

    def patternMatch(op: Operation) = {
        op match {
            case ListOp => listFunction()
            case GrepOp(arguments) => grepFunction(arguments)
            case RemoveOp(arguments) => removeFunction(arguments)
        }
    }

    def listFunction = () => println("Listing all elements")
    def grepFunction = (matchStr: String) => println(s"Grepping elements that matches with $matchStr")
    def removeFunction = (path: String) => println(s"Removing files under this path $path")

    patternMatch(ListOp)
    patternMatch(GrepOp("hello"))
    patternMatch(RemoveOp("/home"))

}
