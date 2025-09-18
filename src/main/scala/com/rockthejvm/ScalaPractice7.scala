package com.rockthejvm

object ScalaPractice7 extends App {
    //Ex3: Using the technique shown in the slides, implement the while loop as a curried function.
    //Now provide do...while functionality (in other words have the test performed at the end of each loop iteration rather
    // than at the beginning) using the same technique.
    //Can you extend further to provide an until loop? This behaves like while, except that it executes the loop body until
    // the control expression evaluates to true .

    def whileFunc(pred: => Boolean)(func: => Unit): Unit = {
        if(pred) {
            func
            whileFunc(pred)(func)
        }
    }
    def doWhileFunc(func: => Unit)(pred: => Boolean): Unit = {
        func
        if(pred) {
            doWhileFunc(func)(pred)
        }
    }
    var i:Int = 0
    whileFunc(i < 5) {
        i += 1
        println("Incrementing i")
    }

    var counter: Int = 5
    doWhileFunc({
        counter += 1
        println("incrementing counter in dowhile func")
    }) (counter < 5)


}
