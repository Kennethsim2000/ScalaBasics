package com.rockthejvm

class HKD(val amount: Double) {
    def +(that: HKD): HKD = new HKD(this.amount + that.amount)
}
object HKD {
    def apply(amount:Double) = new HKD(amount)
}

object CompanionObject1 extends App {
    val a = HKD(100)
    val b = HKD(200)
    val c = a + b
    println(s"Total amount in HKD: ${c.amount}")
}
