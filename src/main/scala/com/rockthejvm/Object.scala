package com.rockthejvm

import java.beans.Expression

object Object extends App {

    class Animal {
        val age: Int = 0
        def eat():Unit = {
            println("I am eating")
        }

    }
    val animal = new Animal

    //inheritance
    //to promote a constructor variable to a field, we need to add in "val", if not newDog.name will not be detected by the compiler
    class Dog(val name: String) extends Animal { // constructor definition
        override def eat():Unit = {
            println("Dog is eating")
        }
    }
    val newDog = new Dog("DogA")
    val aDeclaredAnimal:Animal = new Dog("DogB")
    aDeclaredAnimal.eat();// the method called at runtime depends on the type of the object

    //abstract class
    abstract class walkingAnimal {
        val hasLegs: Boolean = true; // by default public, but we can restrict them by adding private or protected
        def walk():Unit
    }

    //interface
    trait Carnivore {
        def eat(animal: Animal):Unit
    }

    trait Philosopher {
        def ?!(thought: String):Unit // valid method name. We often use ? in akka and ! when we want to communicate with actors asynchronously
    }

    //single class inheritance, multi trait mixing
    class Crocodile extends Animal with Carnivore with Philosopher {
        override def eat(animal: Animal): Unit = println("I am eating you animal")
        override def ?!(thought: String):Unit = println(s"I thinking $thought")
    }

    val aCroc = new Crocodile
    aCroc.eat(newDog)
    aCroc eat newDog //Infix notation = object method argument, only available for methods with one argument
    aCroc ?! "What if we can fly"

    //anonymous class
    val dinosaur = new Carnivore {
        override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat anything")
    }

    //singleton object
    object MySingleton { // the only instance of MySingleton type
        val mySpecialValue: Int = 123
        def mySpecialMethod():Unit = println(123)
        def apply(num: Int): Int = num + 1
    }

    MySingleton.mySpecialMethod()
    MySingleton(65) // equivalent to MySingleton.apply(65)

    object Logger {
        def log(log: String) = println(s"using logger to log $log")
    }
    Logger.log("Testing log")
    /*
    Singleton objects provide a global access point to their methods and properties. Since there is only one instance,
    their members can be accessed from anywhere in the application without the need to create an instance. This is
    particularly useful for utility classes or services that need to be accessed globally .
     */

    object Animal { // class Animal and object Animal are called companions - companion object(has the same name as an existing class/trait)
        //companions can access each other's private fields and methods
        //singleton animal and instances of animal are different
        val canLiveIndefinitely = false
    }
    val canLiveIndefinitely = Animal.canLiveIndefinitely //"static" fields/methods

    class Circle(val radius: Double)

    object Circle {
        def apply(radius: Double): Circle = new Circle(radius)
    }
    //Singleton objects can also be used to define factory methods or apply methods that create instances of the companion class.
    //Singleton objects in Scala are lazily initialized, meaning they are created only when they are first accessed.

    //case class
    //serialization
    //companion class with apply is automatically generated
    //pattern matching
    case class Person(name:String, age:  Int)

    //All constructor parameters in a case class are val by default, making the instances immutable. This ensures that
    // once an object is created, its state cannot be changed

    //case classes may be constructed without the keyword new. Case classes are good for immutable data
    val bob = Person("Bob", 12) // Person.apply("Bob", 12)
    // bob.age = 23 -->This will generate a compile error

    //Case classes are designed to work seamlessly with Scala's pattern matching, allowing for easy deconstruction of
    // objects and extraction of their values.

    //case classes are compared by values rather than reference

    case class Message(sender: String, recipient: String, message: String)

    val message1 = Message("test1", "test2", "Hello world")
    var message2 = Message("test1", "test2", "Hello world")
    println(message1 == message2)

    //you can create a shallow copy of another instance by using the copy keyword
    val copiedMessage = message1.copy(message1.recipient, "new Recipient")
    println(copiedMessage)

    // exception handling
    try {
        val x: String = null
        x.length
    } catch {
        case e: Exception => "Some problem has occurred"
    } finally {
        //execute some code no matter what
    }

    //generics
    abstract class MyList[T] {
        val head: T
        val tail: MyList[T]
    }

    val list:List[Int] = List(1,2,3)
    val head:Int = list.head
    val rest:List[Int] = list.tail

    //In Scala, we usually operate with immutable values.
    //Any modification to an object should return another object
    val reversedList = list.reverse // returns a NEW list

    //case class with pattern matching
    //Sealed traits represent a fixed set of case classes. They can be extended only in the same file and are used to model algebraic data types (ADT)
    sealed trait Expression

    case class Add(lhs: Expression, rhs: Expression) extends Expression
    case class Minus(lhs: Expression, rhs:Expression) extends Expression
    case class Multiply(lhs:Expression, rhs:Expression) extends  Expression
    case class Divide(lhs: Expression, rhs:Expression) extends Expression
    case class Num(num: Int) extends Expression

    def eval(expr: Expression): Int = expr match {
        case Add(leftExpr, rightExpr) => eval(leftExpr) + eval(rightExpr)
        case Minus(leftExpr, rightExpr) => eval(leftExpr) - eval(rightExpr)
        case Divide(leftExpr, rightExpr) => eval(leftExpr) / eval(rightExpr)
        case Multiply(leftExpr, rightExpr) => eval(leftExpr) * eval(rightExpr)
        case Num(num) => num
    }

    val expression = Add(Multiply(Num(2),Num(3)), Add(Num(2), Num(3)))
    val res = eval(expression)
    println(res)


}