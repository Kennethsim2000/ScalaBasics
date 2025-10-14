package com.rockthejvm

import java.time.{LocalDate, Period}
import javax.swing.event.InternalFrameAdapter

class Trade(val id: String, val symbol: String, val quantity: Int, val initialPrice: Double) {
    require(quantity > 0)  // will prevent the object from being constructed by throwing an illegalArgumentException
    override def toString: String = s"Trade id: $id consist of symbol: $symbol, price: $price and quantity: $quantity"
    private var _price = initialPrice
    def price = _price // getter method
    def price_=(value: Double) = { // setter method
        if(value >= 0) {
            _price = value
        }
    }
    def value() = {
        quantity * price
    }
}

object Trade {
    def apply(id: String, symbol: String, quantity: Int, initialPrice: Double): Trade
    = new Trade(id, symbol, quantity, initialPrice)
}

case class Trade2(val id: String, val symbol: String, val quantity: Int, var price: Double)

class Person(val dob: LocalDate, val name:String) {
    def age: Int = {
        val period = Period.between(dob, LocalDate.now())
        period.getYears
    }
}

class Fraction private(val numerator: Int, val denominator: Int) {
    def *(that: Fraction): Fraction = {
        new Fraction(numerator * that.numerator, denominator * that.denominator)
    }

    override def toString: String = s"Fraction($numerator/$denominator)"
}

object Fraction {
    def apply(numerator:Int, denominator: Int) = {
        if(denominator != 0) {
            new Fraction(numerator, denominator)
        } else {
            throw new IllegalArgumentException("Denominator cannot be zero")
        }
    }
}

object ObjectOrientedExample extends App {
    val trade = Trade("id", "FB", 100, 10.0)
    trade.price = 20.0
    println(trade)
    println(trade.value())
    val trade2 = Trade2("id", "FB", 100, 10.0)
    trade2.price = 30.0
    println(trade2)
    val person: Person = Person(LocalDate.of(1990, 5, 15), "kenneth")
    println(person.age)

    val f1 = Fraction(2,3)
    val f2 = Fraction(3,4)
    val f3 = f1 * f2
    println(f3)

}
