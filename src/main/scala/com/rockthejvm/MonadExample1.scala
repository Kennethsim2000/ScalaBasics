package com.rockthejvm


object MonadExample1 extends App {
    sealed trait Box[A] {
        def map[B](f: A => B) = {
            this match {
                case Empty() => Empty[B]()
                case Full(v) => Full[B](f(v))
            }
        }
        // same as option fold, if empty, return provided empty, else apply function
        def fold[B](empty: B)(f: A => B): B = this match {
            case Empty() => empty
            case Full(v) => f(v)
        }

        def flatMap[B](f: A => Box[B]): Box[B] = this match {
            case Empty() => Empty[B]()
            case Full(v) => f(v)
        }
    }

    case class Empty[A]() extends Box[A]

    case class Full[A](val value: A) extends Box[A]

    val e: Box[Int] = Empty()
    val f: Box[Int] = Full(3)

    e.flatMap(x => Full(x * 2)) // Empty()
    f.flatMap(x => Full(x * 2)) // Full(6)


}
