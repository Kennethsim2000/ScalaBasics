package com.rockthejvm

object ScalaPractice9 extends App {

    //Ex1: Define a simple ADT to represent days of the week. Implement the methods tomorrow and nextWorkingDay for your type.
    sealed trait Day
    case object Monday extends Day
    case object Tuesday extends Day
    case object Wednesday extends Day
    case object Thursday extends Day
    case object Friday extends Day
    case object Saturday extends Day
    case object Sunday extends Day

    def tomorrow(today: Day) = today match {
            case Monday => Tuesday
            case Tuesday => Wednesday
            case Wednesday => Thursday
            case Thursday => Friday
            case Friday => Saturday
            case Saturday => Sunday
            case Sunday => Monday
    }

    def nextWorkingDay(today: Day) =  today match {
            case Monday => Tuesday
            case Tuesday => Wednesday
            case Wednesday => Thursday
            case Thursday => Friday
            case Friday | Saturday | Sunday=> Monday
    }

    val friday = Friday
//    println(tomorrow(friday))
//    println(nextWorkingDay(friday))

    //Ex2: What changes would you need to make to make the Box type covariant in its type parameter? Make the same changes to the MyList type.
    sealed trait MyList[+T] {
        def fold[B](initial: B, func: (B, T) => B ): B
    }

    case object emptyList extends MyList[Int] {
        override def fold[B](initial: B, func: (B, Int) => B): B = initial

    }

    case class nonEmptyList(head: Int, tail: MyList[Int]) extends MyList[Int] {
        // what fold does is that it provides an initial, and every element, it will call func on the element
        override def fold[B](initial: B, func: (B, Int) => B): B = {
            val acc = func(initial, head)
            tail.fold(acc, func)
        }
    }

    val myList = nonEmptyList(1, nonEmptyList(2, nonEmptyList(3, emptyList)))
    val sum = myList.fold(0, (a, b) => a + b)
    println(sum)

}


//What changes would you need to make to make the Box type covariant in its type parameter? Make the same changes to the MyList type.
//Using the fold method that was defined on the MyList (A] type, implement the following methods:
//contains (el: A)|
//(does the list contain el?)
//drop (i: Int) (remove the first i elements from the list) |
//dropWhile(o: A Boolean) (remove elements from the front of the list until predicate o is false)
//Look at the documentation for the built-in List (+A) class. Use the foldLeft method to implement reverse on an instance of this class. Can you add this capability to the MyList (A) class?
//Binary tree is a type that can also be defined easily using a recursive ADT: A tree is o a leaf node, which contains a value
//o an internal node, which contains a left node and a right node
//Implement this type, and provide a fold method for it. Use the fold method to implement length, which returns the number of values (leaf nodes) in the tree