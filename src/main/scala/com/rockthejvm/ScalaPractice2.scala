package com.rockthejvm

import scala.math.pow

object ScalaPractice2 extends App {
    //Ex 1: Write a function that takes as its argument a String value describing an operation to be carried out on a pair of integers.
    //For example: add, subtract, power.
    //Return a function that will perform the required operation on two Ints, returning an Int result.
    def operation(op: String): (Int, Int) => Int = {
        op match {
            case "add" => (x, y) => x + y
            case "subtract" => (x, y) => x - y
            case "power" => (x, y) =>  pow(x,y).asInstanceOf[Int]
        }
    }

//    val addFunc = operation("add")
//    println(addFunc(2,3)) // should be 5
//    val subtractFunc = operation("subtract")
//    println(subtractFunc(3,2)) // should be 1
//    val powerFunc = operation("power")
//    println(powerFunc(2,3)) // should be 8

    // Ex2: Removing an element from a collection is an operation that is not directly supported by the main Sequence based collection type.
    // Investigate the methods provided by Sequence collections, and use them to write a function
    //1. remove(coll: Seq, idx: Int) that removes the element at index position iax from the collection.
    // Have your collection operate with both mutable or immutable collections, by returning a new collection with the element removed.
    //Clue: Research into sequence and how to remove elements from sequence

    def remove(col: Seq[Int], index: Int): Seq[Int] = {
        col.patch(index, Nil, 1) // this helps us replace a sequence of elements in a Seq with another sequence(in this case we are replacing with nil)
    }

    val seq1 = Seq(1,2,3,4,5)
    val seqRemoved = remove(seq1, 1)
    println(seqRemoved) // 1,3,4,5


}
