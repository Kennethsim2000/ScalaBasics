package com.rockthejvm

class Trade(val id: String, val symbol: String, val quantity: Int, val initialPrice: Double) {
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

object ObjectOrientedExample extends App {
    val trade = Trade("id", "FB", 100, 10.0)
    trade.price = 20.0
    println(trade)
    println(trade.value())
    val trade2 = Trade2("id", "FB", 100, 10.0)
    trade2.price = 30.0
    println(trade2)
}
