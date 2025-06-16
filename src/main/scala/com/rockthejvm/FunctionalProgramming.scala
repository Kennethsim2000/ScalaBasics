package com.rockthejvm

object FunctionalProgramming extends App {
    class Person(name: String) {
        def apply(age: Int): Unit = println(s"I have aged $age years")
    }
    val bob = new Person("Bob")
    bob(43)
    bob.apply(43)

    val simpleIncrementor = new Function[Int, Int] {
        override def apply(num: Int): Int = num + 1
    }
    simpleIncrementor(23)
    simpleIncrementor.apply(23)

    val stringConcatenator = new Function2[String, String, String] {
        override def apply(str1: String, str2: String): String = str1 + str2
    }

    stringConcatenator("I love", " Scala")

    //syntax sugar
    val doubler:Function1[Int,Int] = (num:Int) => num * 2
    val doublerShort: (Int => Int) = (num:Int) => num * 2
    val returnOmmited = (num: Int) => num * 2
    println(doubler(2))
    println(doublerShort(2))
    println(returnOmmited(2))
    val addTwoNum = (num1: Int, num2:Int) => num1 + num2
    println(addTwoNum(2,3))

    //higher order functions takes functions as argument
    val mappedList = List(1,2,3).map(x=> x + 1)
    println(mappedList)
    val flatMappedList = List(1,2,3).flatMap(x=> List(x, x + 1))
    println(flatMappedList)
    val filteredList = List(1,2,3,4,5).filter(_ <= 3) // equivalent to x => x <= 3
    println(filteredList)

    val allPairs = List(1,2,3).flatMap(number=> List("a", "b", "c").map(letter=> s"$number-$letter"))
    println(allPairs)

    //for comprehension
    val alternativePairs = for {
        number <- List(1,2,3)
        letter <- List("a", "b", "c")
    } yield s"$number-$letter"
    println(alternativePairs)

    case class User(name:String, age:Int)
    val user1 = User("A", 19)
    val user2 = User("B", 13)
    val user3 = User("C", 20)

    val usersAbove18 = for {
        user <- List(user1, user2, user3)
        if user.age > 18
    } yield user
    println(usersAbove18)

    //lists
    val lst = List(1,2,3,4,5)
    val firstElem = lst.head
    val restOfList = lst.tail
    val prependedList = 0 :: lst  // (0,1,2,3,4,5)
    val extendedList = 0 +: lst :+ 6 // (0,1,2,3,4,5,6)
    val lastElem = extendedList(6)
    println(extendedList(3))
    println(s"printing the last character $lastElem")

    //sequence
    val sequence: Seq[Int] = Seq(1,2,3,4,5 )
    val accessedElement: Int = sequence(4)

    //vector
    val vector = Vector(1,2,3,4,5)

    //sets = no duplicate
    val set = Set(1,2,3,4,5)
    val setHasFive = set.contains(5)
    println(setHasFive)
    val addedSet = set + 6
    val removeSet = set - 3

    //ranges
    val range = 1 to 1000
    val twoByTwo = range.map(x=> x * 2).toList

    //tuples
    val tuple = ("John", "Cena", 1982)

    //maps
    val phoneBook: Map[String, Int] = Map(
        ("Daniel"-> 123213213),
        ("Tom"-> 1212321345)
    )

    //higher order function
    def calculateAnything(num: Int, calcFunction: Int => Int): Int = {
        calcFunction(num)
    }
    def square(num:Int):Int = {
        num * num
    }
    println(calculateAnything(2, square))

    /**
     * Reduce vs ReduceLeft
     *
     * The operation in reduce must be associative, meaning that you can split the list into several segments, apply
     * operation to each of them separately, and then apply it again to the results to combine.
     *
     * This is important when you want to do things in parallel: reduce can be applied in parallel to different parts
     * of the list, and then the results can be combined.
     *
     * reduceLeft on the other hand, can only work sequentially (left to right, as the name implies), operation does not
     * have to be associative, and can expect its second parameter to always be an element of the sequence
     * (and the first one – result of the operation so far);
     */

    val rangeInt = 1 to 100
    val sum = rangeInt.reduce(_ + _)
    println(sum)
    val strings = (1 to 100).reduceLeft[Any](_.toString + ";" + _.toString)
    println(strings)

}
