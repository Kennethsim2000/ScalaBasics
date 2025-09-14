package com.rockthejvm

import scala.annotation.tailrec

object ScalaPractice6 extends App {
    //Ex3: Implement a function, timed, that allows a caller to measure the time taken to evaluate an expression, which is passed to it as an argument.
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

    // Ex4: Write a recursive function fib(n: Int): Int that uses a recursive algorithm to generate the nth Fibonacci number.
    // Do you think the return value of Int will be sufficient? How would you change this to make it more useful.

    //Use this function in a further function that generates the first n numbers in the Fibonacci sequence.
    // Test with the Int return value for your fib function from above, then with your alternative.

    //How many elements of the sequence can you practically generate?
    //Rewrite the function to generate the Fibonnaci numbers to be tail recursive (verify with the @tailrec annotation).
    //Use the timed function from earlier to measure the relative performance of the normal recursive and the tail recursive implementation.
    //See if you can produce a set of data as a sequence of Pairs, (n, time-to-evaluate-fib(n) for each, in order to examine
    // the effectiveness in terms of scalability of your algorithms.

    //Clue: Create a recursive function for fibonnaci numbers(using Int, then use BigInt), then rewrite it using @tailrec,
    // note that we have to not have any * x but rather encapsulate it within the parameter.
    def fib(n:Int): BigInt = {
        if(n == 1) {
            1
        } else {
            n * fib(n - 1)
        }
    }

    @tailrec
    def fibRec(n: Int, sum: BigInt):BigInt = {
        if(n == 0) {
            sum
        } else {
            fibRec(n - 1, n * sum)
        }
    }

    def fibSeq(num: Int): Seq[BigInt] = {
        (1 to num).map(x => fib(x))
    }

    val res = fibSeq(10)
    println(res)

    val elapsedTime2 = timed {
        fib(100)
    }
    val elapsedTime3 = timed {
        fibRec(100,1)
    }
    println(s"Elapsed time for non tail recursive is $elapsedTime2")
    println(s"Elapsed time for tail recursive is $elapsedTime3")

}
