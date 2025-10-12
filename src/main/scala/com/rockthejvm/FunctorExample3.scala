package com.rockthejvm

import com.rockthejvm.FunctorExample.{Box, Full, Empty}

object FunctorExample3 extends App {

    trait Functor[F[_]] {
        def map[A, B](fa: F[A])(f: A => B): F[B]
    }
    //For any type constructor F[_] (like List, Seq, Option, etc.), if we can define how to map a function over it, then it’s a functor.

    object SeqF extends Functor[Seq] { // defining our functors based on existing functor type
        override def map[A, B](fa: Seq[A])(f: A => B): Seq[B] = fa.map(f)
    }

    val l = List(1,2,3)
    val el: List[Int] = Nil
    val sl = List("Foo", "Bar")

    SeqF.map(l)(_ * 2)
    SeqF.map(el)(_ * 2)
    SeqF.map(sl)(_.length)

    object BoxF extends Functor[Box] {
        override def map[A, B](fa: Box[A])(f: A => B): Box[B] = fa.map(f)
    }

    val b: Box[Int] = Full(2)
    val eb: Box[Int] = Empty()
    val sb: Box[String] = Full("hello")

    println(BoxF.map(b)(_ * 2))
    println(BoxF.map(eb)(_ * 2))
    println(BoxF.map(sb)(_.toUpperCase))
}
