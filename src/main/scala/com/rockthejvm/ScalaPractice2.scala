package com.rockthejvm

import scala.collection.mutable.ListBuffer
import scala.math.pow
import scala.util.Random

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
//    println(seqRemoved) // 1,3,4,5

    //Ex 3. Write a program that plays the UK National Lottery. This involves selecting 6 random integer between 1 and 49.
    // Clearly the numbers selected must be unique. Investigate the Random type for selecting numbers, and choose an
    // appropriate collection type to store the numbers. Display the numbers in ascending order.
    //Clue: Explore the random type, and insert elements into the collection
    val random = new Random()

    // This version guarantees uniqueness
    val shuffledList = random.shuffle(1 to 49).take(6).sorted
    println(shuffledList)

    // This version does not guarantee uniqueness
    val myList = List.fill(6)(random.between(1,49))
    val newList = myList.sorted
    println(newList)





}
