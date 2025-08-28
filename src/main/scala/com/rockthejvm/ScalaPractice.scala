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



}
