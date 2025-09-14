package com.rockthejvm

object ScalaPractice6 extends App {
    //Implement a function, timed, that allows a caller to measure the time taken to evaluate an expression, which is passed to it as an argument.
    // In other words:
    //val timelExecute = timed do Something}
    //For the moment, assume that the expression is of type Unit.
    //Use the function System.nanoTime to find the time in nanoseconds. This works like System.currentTimeMillis,
    // but at a higher resolution. It may not always be accurate to nanoseconds (this is platform dependent) however
    // it is useful for measuring time intervals.
    //Clue: Create a function called timed that takes in a call by name function that returns unit

    def timed(func : => Unit): Long = {
        val startTime = System.nanoTime()
        func
        val endTime = System.nanoTime()
        val timeTaken = endTime - startTime
        timeTaken
    }

    val elapsedTime = timed {
        (1 to 100000).sum
    }
    println(s"Elapsed time is $elapsedTime")
}
