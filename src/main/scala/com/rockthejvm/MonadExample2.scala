package com.rockthejvm

import com.rockthejvm.MonadExample1.{Box, Full, Empty}


object MonadExample2 extends App {
    trait Monad[M[_]] {
        def flatMap[A,B](fa: M[A])(f: A => M[B]): M[B]
        def unit[A](a: => A): M[A]
    }

    object SeqM extends Monad[Seq] {
        override def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = seq flatMap f

        override def unit[A](a: => A): Seq[A] = Seq(a)
    }

    val l = List(1,2,3)
    val el: List[Int] = Nil
    val sl = List("Foo", "bar")

    SeqM.flatMap(l)(i => 1 to i) // List(1,1,2,1,2,3)
    SeqM.flatMap(el)(i => 1 to i) // List()
    SeqM.flatMap(sl)(_.toSeq) // List(F,O,O,B,A,R)

    object BoxM extends Monad[Box] {

        override def flatMap[A, B](box: Box[A])(f: A => Box[B]): Box[B] = box flatMap f

        override def unit[A](a: => A): Box[A] = Full(a)
    }

    val b: Box[Int] = Full(2)
    val eb: Box[Int] = Empty()
    val sb: Box[String] = Full("hello")

    println(BoxM.flatMap(b)(i => BoxM.unit(i * 2))) // Full(4)
    println(BoxM.flatMap(eb)(i => BoxM.unit(i * 2))) // Empty()
    println(BoxM.flatMap(sb)(i => BoxM.unit(i.toUpperCase))) // Full(HELLO)


}
