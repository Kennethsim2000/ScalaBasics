package com.rockthejvm

object ScalaPractice5 extends App {

    //Ex 2. We can often define a List type as a Sum type, using recursive techniques:
    //"A list is either empty or it is an element followed by another list"
    //Define a type ListOfint in this way - the elements must be of type Int for now
    //Add the following methods to your ListOfint type: count - returns the number of elements in the type
    // contains(e) - returns true if the list contains the argument e sum - return the sum of all the elements in the list
    // add(e) - add element e to the List
    //Generalise this list into a generic type, where the type parameter represents element type
    //Make the list covariant in the element type.
    //Clue: have a sealed abstract class ListOfint with the count, contains, sum and add method, case object Emptyint extends ListOfint
    //case class Consint(head: Int, tail: ListOfint) extends ListOflnt

    sealed abstract class ListOfInt {
        val count:Int
        def contains(num:Int):Boolean
        def add(num: Int):ListOfInt
        def printList(): Unit
    }

    case object EmptyList extends ListOfInt {
        override val count: Int = 0
        override def contains(num: Int): Boolean = false
        override def add(num: Int): ListOfInt = NonEmptyList(num, EmptyList)

        override def printList(): Unit = {
            println(".")
            println("end of list")
        }
    }

    case class NonEmptyList(head:Int, tail: ListOfInt) extends ListOfInt {
        override val count: Int = 1 + tail.count
        override def contains(num: Int): Boolean =
            if(head == num) {
                true
            } else {
                tail.contains(num)
            }

        override def add(num: Int): ListOfInt = NonEmptyList(num, tail.add(head))

        override def printList(): Unit = {
            print(head + ",")
            tail.printList()
        }

    }

    val emptyLst = EmptyList
    val oneObject = emptyLst.add(1)
    val twoObject = oneObject.add(2)
    println(twoObject.contains(2)) // should be true
    println(twoObject.contains(3)) // should be false
    twoObject.printList()


    //Making it generic
    case class People(name:String, age:Int)

    sealed abstract class ListOfGeneric[T] {
        val count: Int
        def contains(elem: T): Boolean
        def add(elem: T): ListOfGeneric[T]
        def printList(): Unit
    }

    case object EmptyListPeople extends ListOfGeneric[People] {
        override val count: Int = 0
        override def contains(elem: People): Boolean = false
        override def add(elem: People): ListOfGeneric[People] = NonEmptyListPeople(elem, EmptyListPeople)
        override def printList(): Unit = {
            println(".")
            println("end of list")
        }
    }

    case class NonEmptyListPeople(head: People, tail: ListOfGeneric[People]) extends ListOfGeneric[People] {
        override val count: Int = 1 + tail.count

        override def contains(elem: People): Boolean =
            if (head == elem) {
                true
            } else {
                tail.contains(elem)
            }

        override def add(elem: People): ListOfGeneric[People] = NonEmptyListPeople(elem, tail.add(head))

        override def printList(): Unit = {
            print(s"$head + ")
            tail.printList()
        }
    }

    val emptyLstPeople = EmptyListPeople
    val person1 = People("Kenneth", 25)
    val person2 = People("Jamie", 21)
    val person3 = People("NotInside", 25)
    val onePersonList = emptyLstPeople.add(person1)
    val twoPersonList = onePersonList.add(person2)
    println(twoPersonList.contains(person2)) // should be true
    println(twoPersonList.contains(person3)) // should be false
    twoPersonList.printList()
}
