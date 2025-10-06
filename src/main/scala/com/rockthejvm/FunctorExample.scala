package com.rockthejvm

object FunctorExample extends Object {
    sealed trait Box[A] {
        def map[B](f: A => B) = {
            this match {
                case Empty() => Empty[B]()
                case Full(v) => Full[B](f(v))
            }
        }

        // same as option fold, if empty, return provided empty, else apply function
        def fold[B](empty: B)(f: A => B) = this match {
            case Empty() => empty
            case Full(v) => f(v)
        }
    }

    case class Empty[A]() extends Box[A]

    case class Full[A](val value: A) extends Box[A]

}
