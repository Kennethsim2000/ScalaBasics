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

    

}
