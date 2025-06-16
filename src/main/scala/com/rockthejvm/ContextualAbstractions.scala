package com.rockthejvm

object ContextualAbstractions {

    // Context parameters/ arguments
    val unorderedList = List(2,1,3,4)
    val orderedList = unorderedList.sorted // (Ordering) fetched by compiler and automatically injected into sorted method
    given descOrdering: Ordering[Int] = Ordering.fromLessThan( _ > _)
    // (a,b) => a > b -> basically it means a can go before b if a is greater than b
    // by putting given, the compiler will automatically fetch this for all methods that takes in an Ordering[Int}
    //analogous to a implicit val
    println(orderedList)

    trait Combinator[A] {
        def combine(x: A, y: A) : A
    }

    //using and given is the same as implicit in scala 2.

    def combineAll[A](list: List[A])(using combinator: Combinator[A]): A = // we use the using keyword for contextual argument
        list.reduce((a,b)=> combinator.combine(a,b))

    given intCombinator:Combinator[Int] = new Combinator[Int] {
        override def combine(x: Int, y: Int): Int = x + y
    }

    trait Logger[A] {
        def log(log: A): Unit
    }

    def logStatement[A](list: List[A])(using logger: Logger[A]) =
        list.forall(item=> {
            logger.log(item)
            true
        })

    given stringLogger: Logger[String] = new Logger[String] {
        override def log(item: String) = println(item)
    }

    logStatement(List("hello", "world"))


    /**
     * Compiler looks for the given instances:
     * Local scope
     * Imported  scope: import your package.given, we import all the given instances from that package
     * The companions of all the types involved in the call
     */

    val sum = combineAll(List(1,2,3,4,5)) // compiler will automatically pass the intCombinator

    //context bounds
    def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
    //Takes a generic function that operates on a list of type A. using Combinator[A] is a context function parameter introduced in scala 3.
    //The using keyword indicates that the compiler should provide this value implicitly when the function is called
    def combineAll_v3[A:Combinator](list:List[A]):A = ???

    /*
    where context args are useful
    -type classes
    -dependence injection
    -context dependent functionality(Contextual abstraction for using code for some types and not other types)
    -Type level programming
     */

    /*
    Extension methods
     */
    case class Person(name:String) {
        def greet():String = s"hi my name is $name"
    }
    val danielGreeting = "Daniel".greet() // the compiler would search for all possible extension methods on the string type.

    extension(string: String) {
        def greet():String = new Person(string).greet()
    }

    extension[A](list: List[A]) {
        def combineAllValues(using combinator: Combinator[A]) = {
            list.reduce(combinator.combine)
        }
    }
    def aSum_v2 = unorderedList.combineAllValues

    case class Circle(radius: Int)

    extension(circle: Circle) {
        def circumference(): Double = math.Pi * 2 * circle.radius
    }
    val circle = Circle(2)
    val circumference = circle.circumference()
    println(s"circumference is $circumference")


    def main(args: Array[String]): Unit = {
        println(orderedList)
        println(sum)
        println(aSum_v2)
    }

    /**
     * Implicit arguments
     */
    case class Color(color:String)
    case class DrawingDevice(name:String)

    def colorObject(colorObject: String)(implicit color: Color, device: DrawingDevice ) = {
        println(s"Coloring $colorObject ${color.color} color with ${device.name}")
    }

    implicit val redColor: Color = Color("Red")
    implicit val pen: DrawingDevice = DrawingDevice("Pen")
    colorObject("notebook")

    /**
     * A real-world example is the database’s create, read, update, and delete (CRUD) operations:
     * The crucial part of these operations is the record itself, but often we have to carry the database connection
     * along with the code. And because of that, the database connection is a good candidate for the implicit parameters
     * in CRUD operations.
     *
     * // without implicit parameter
     * model.create(conn, newRecord)
     *
     * // with implicit parameter
     * model.create(newRecord)
     */
}
