package com.rockthejvm

abstract class TradeAbstract(val id: String, val initialPrice: Double) {
    override def toString: String = s"Trade id: $id consist of price: $price"

    private var _price = initialPrice

    def price = _price // getter method

    def price_=(value: Double) = { // setter method
        if (value >= 0) {
            _price = value
        }
    }


    def isExecutable: Boolean
}

class EquityTrades(id: String, val symbol: String, val quantity: Int, initialPrice: Double) extends TradeAbstract(id, initialPrice) {
    override def isExecutable: Boolean = true

    override def toString: String = {
        s"EquityTrade(id: $id, symbol: $symbol, quantity: $quantity, price: $price)"
    }

    def value() = {
        quantity * price
    }

}

class FxTrades(id: String, initialPrice: Double) extends TradeAbstract(id, initialPrice) {
    override def isExecutable: Boolean = false

    override def toString: String =
        s"FxTrade(id: $id, price: $price)"

}

trait FeePayable {
    def fee: Double = 10.0
}

trait Taxable {
    def tax:Double
}

class Transaction(id: String, symbol: String, quantity: Int, initialPrice: Double) extends
    EquityTrades(id, symbol, quantity, initialPrice) with FeePayable with Taxable {
    def amount() = {
        tax + value() + fee
    }

    override def tax: Double = {
        val valueTrade = value() + 10
        valueTrade * 0.125
    }
}


object ObjectOrientedExample2 extends App {
    val t1 = Transaction("1", "FB", 10, 10.0)
    println(t1.amount())
    println(t1.tax)
}
