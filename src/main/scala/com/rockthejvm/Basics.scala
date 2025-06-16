package com.rockthejvm

object Basics extends App {
    val meaningOfLife: Int = 42; // val means that this variable cannot be reassigned
    val aBoolean = false; // compiler infers type for us
    val aString = "I love Scala";
    val interpolatedString = s"The meaning of life is $meaningOfLife";

    val expressionExample = 2 + 3 // scala is composed of expressions
    //if expression, it is an expression because it reduces to a single value
    val ifExpression = if (meaningOfLife > 43) 56 else 999;
    //chaining multiple if condition
    val chainedIfExpression = if (aString.equals("I love Scala")) "Scala"
    else if (aString.equals("I love java")) "Java"
    else if (aString.equals("I love golang")) "Golang"
    else "C++"

    //code block
    val codeBlock = {
        val num = 3
        //value of code block is the value of the last expression
        num + 3
    }

    //define a function
    def myFunction(x:Int, y:String): String = {
        x + " " + y
    }

    //recursive function
    //In Scala, we don't use loops or iteration, we use recursion
    private def factorial(x: Int): Int = {
        if(x == 1) {
            1
        }
        else x * factorial(x - 1)
    }

    //Unit type is the equivalent of void in other languages
    println("Hello")

    def functionReturnUnit(): Unit = {
        println("Returning void")
    }
}
