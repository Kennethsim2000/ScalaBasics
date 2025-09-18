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

    //Ex4. Given a seq of partial functions, how can we efficiently combine them together into a single partial function that encompasses all inputs?
    //The right hand operand of the catch operator in exception handling in Scala is in fact a PartialFunction [Throwable, T], where T depends on the action
    // performed on catching the exception. For example, the catch "block" is a PartialFunction[Throwable, Unit] .
    //Define a partial function that encapsulates the behaviour shown above in a val. Define a second partial function that encapsulates the handling of
    // another exception such as ArithmeticException (or a group of related exceptions) in a second val. Experiment using these partial functions as the right
    // hand operand of catch in some sample code.
    //Write a function that accepts, as one of it arguments, a sequence of PartialFunction| Throwable, T1 instances, indicating exceptions that should be caught
    // within the function. Test by calling the function with different exceptions to catch each time, and verify that they are being caught

    //Clue: partial function has the orElse method to combine. We can define a partial function that takes in a throwable and returns unit, and the case can be
    // for example exception:NullPointerException or exception:ArithmeticException, and we can just catch our partial function

    val ArithmeticExceptionHandler: PartialFunction[Throwable, Unit] = {
        case e:ArithmeticException => println("code encountered arithmetic exception")
    }
    val NullPointerExceptionHandler: PartialFunction[Throwable, Unit] = {
        case e:NullPointerException => println("Code encountered null pointer exception")
    }
    try {
        val myString: String = null
        println(myString.length)
    } catch {
        ArithmeticExceptionHandler orElse NullPointerExceptionHandler
    }

    val caseEven: PartialFunction[Int, String] = {
        case x if x % 2 == 0 => "Number is even"
    }
    val caseOdd: PartialFunction[Int, String] = {
        case x if x % 2 != 0 => "Number is odd"
    }
    val combinedCases = caseEven orElse(caseOdd)
    println(combinedCases(3))
    println(combinedCases(4))

    def combinePartialFunc(lst: Seq[PartialFunction[Int, String]]): PartialFunction[Int, String] = {
        lst.reduce((x,y)=> x orElse y)
    }
    val combinedFunc = combinePartialFunc(Seq(caseOdd, caseEven))
    println(combinedFunc.apply(3))
    println(combinedFunc.apply(4))

}
