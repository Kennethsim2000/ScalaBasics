package com.rockthejvm

object ScalaPractice11 extends App{
    trait Monoid[A] {
        def append(f1: A, f2: A): A
        def zero: A
    }

    object IntMonoid extends Monoid[Int] {
        override def append(f1: Int, f2: Int): Int = f1 + f2
        override def zero: Int = 0
    }

    // Associative
    IntMonoid.append(1, IntMonoid.append(2,3)) // 6
    IntMonoid.append(IntMonoid.append(1,2),3) // 6

    // Identity
    IntMonoid.append(IntMonoid.zero, 2) // 2

    // Fold
    val lst: List[Int] = List(1,3,4)
    val sum = lst.foldLeft(IntMonoid.zero)(IntMonoid.append) // 8
    val sum2 = lst.foldRight(IntMonoid.zero)(IntMonoid.append) // 8

}
