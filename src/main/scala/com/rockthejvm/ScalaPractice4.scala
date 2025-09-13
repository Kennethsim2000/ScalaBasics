package com.rockthejvm

import scala.math._

object ScalaPractice4 extends App {
    //Ex1: Certain arithmetic operations are not defined for all possible input values. For example, divide is not
    // defined when the divisor is 0, square root is not defined when the input number is negative.
    //Implement versions of, for example, divide and square root, as functions that return Option! values.
    //Return the Some(value) if the function is defined on the input(s), and None if not.
    //Implement a further function that applies one of your functions above to each element of a Seq of inputs,
    // generating a Seq of outputs for the valid input values.

    def divide(num:Int, divisor:Int): Option[Double] = {
        if(divisor == 0) {
            None
        } else {
            Some(num / divisor)
        }
    }

    def squareRoot(num: Int): Option[Double] = {
        if(num < 0) {
            None
        } else {
            Some(sqrt(num))
        }
    }

    def applySeq(seq: Seq[Int]): Unit = {
        val appliedList: Seq[Double] = seq.flatMap(x => squareRoot(x))
        // flatmap will call map, followed by flatten, map will receive a Seq[Option[Double]], while flatten will help to remove all the None values
        print("item of new list of valid values is ")
        appliedList.foreach(x=> print(s"$x,"));
        println();
    }

    val seqSquareRoot = Seq(-1, -4, 4, 1, 9, -9);
    applySeq(seqSquareRoot)
}

