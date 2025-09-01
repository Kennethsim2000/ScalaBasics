package com.rockthejvm

import java.io.File
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
//    println(shuffledList)

    // This version does not guarantee uniqueness
    val myList = List.fill(6)(random.between(1,49))
    val newList = myList.sorted
//    println(newList)

    //Ex 4:
    //Generate a list of the names of all the _files_ in the current directory, excluding the "hidden" files (i.e. those whose names start with ".").
    //Generate two lists of entries in the directory, one containing the files and one containing the directories.
    //Generate a list of Pair objects, each containing the name of a file and its size in bytes.

    // TODO: COMPLETE THE REST
    //Generate a list of the 10 smallest_files_ in the directory, in order, together with their sizes. Do the same for the 10 largest files.
    //Modify your answer to the above to return a Map where the keys are the names and the values are the sizes Generate
    // a data structure that arranges the contents of the directory according the the first letter of their name. In other words,
    // for letter 'a' there should be a list of the entries with names beginning with 'a', and so on...

    val currDir = new File(".")
    val filesAndDir = currDir.listFiles().toList;
    val filesInCurrDir = filesAndDir.filter(_.isFile)
    val fileNames = filesInCurrDir.map(_.getName)
    val fileSizes = filesInCurrDir.map(_.length())
    println(filesAndDir)
    println(filesInCurrDir)

    val filePairs = for {
        file <- filesInCurrDir
    } yield (file.getName, file.length())
    val filePairs2 = fileNames.zip(fileSizes)
    println(filePairs)
    println(filePairs2)








}
