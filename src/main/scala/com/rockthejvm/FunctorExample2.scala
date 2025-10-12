package com.rockthejvm

import com.rockthejvm.Object.MyList

object FunctorExample2 extends App {

    sealed trait MyList[A] {

        def map[B] (f: A => B): MyList[B] = this match {
            case End() => End[B]()
            case Cons(head, tail) => Cons(f(head), tail.map(f))
        }
    }

    case class Cons[A](val head: A, val tail: MyList[A]) extends MyList[A]

    case class End[A]() extends MyList[A] // we cannot have generic type on case object, so we have to use case class

    val l1: MyList[Int] = Cons(1, Cons(3, Cons(5, End())))
    val s1: MyList[String] = Cons("a", Cons("b", End()))
    println(l1.map(x => x * x))
    println(s1.map(_.length))
    val e1: MyList[Int] = End()
    println(e1.map(_ + 2))
}
