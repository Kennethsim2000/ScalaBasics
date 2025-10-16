package com.rockthejvm

import scala.collection.mutable.ListBuffer

object GenericsExample extends App {

    sealed trait Fruit
    class Apple extends Fruit {
        override def toString: String = "Apple"
    }

    class Banana extends Fruit {
        override def toString: String = "Banana"
    }

    class MyBox[T](val lst: Seq[T]) { // this is invariant
        def insert(item: T): MyBox[T] = {
            val newSeq = lst :+ item
            new MyBox[T](newSeq)
        }

        def get(): Seq[T] = lst

        override def toString: String = s"MyBox:${lst.mkString(",")}"
    }

    class MyBoxCovariant[+T](val lst: Seq[T]) { // this is invariant
//        def insert(item: T): MyBox[T] = {
//            val newSeq = lst :+ item
//            new MyBox[T](newSeq)
//        }

        def get(): Seq[T] = lst

        override def toString: String = s"MyBox:${lst.mkString(",")}"
    }

    class MyBoxContravariant[-T](lst: ListBuffer[T]) { // this is invariant
        def insert(item: T):Unit = {
           lst += item
        }
        override def toString: String = s"MyBox:${lst.mkString(",")}"

        def print() = {
            lst.foreach(println(_))
        }
    }


    val apple1 = new Apple
    val apple2 = new Apple
    val apple3 = new Apple
    val box1 = MyBox[Fruit](Seq(apple1, apple2))
    val newBox = box1.insert(apple3)
    println(newBox.get())

    val box2 = MyBoxCovariant[Apple](Seq(apple1, apple2))
    println(box2.get())
    val box3: MyBoxCovariant[Fruit] = box2
    println(box3.get())


    val box4 = MyBoxContravariant[Fruit](ListBuffer(apple1, apple2))
    val banana1 = new Banana
    box4.insert(banana1)
    box4.print()

    val box5:MyBoxContravariant[Apple] = box4
//    box5.insert(banana1) --> Cannot insert banana into apple






}
