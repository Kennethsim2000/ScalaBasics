package com.rockthejvm

object PatternMatching extends App{
    val orderNumber = 55
    val order = orderNumber match {
        case 1 => "first"
        case 2 => "second"
        case 3 => "third"
        case _ =>  orderNumber + "th"
    }
    // pattern match is an expression, which can be reduced to a value
    println(order)

    def matchTest(num: Int): String = {
        num match {
            case 1 => "one"
            case 2 => "two"
            case _ => "other"
        }
    }
    println(matchTest(orderNumber))

    //case class decomposition
    case class Person(name: String, age: Int)

    val bob = Person("Bob", 32)
    val personGreeting = bob match { // pattern matching is available in case classes
        case Person(name, age) => s"Hello my name is $name and i am $age years old"
        case _ => "Unrecognised structure"
    }
    println(personGreeting)

    //decomposing tuples
    val band = ("Bon Jovi", "Rock")
    val bandDescription = band match {
        case (band, genre) => s"band $band belongs to genre $genre"
        case _ => "unrecognised structure"
    }
    println(bandDescription)

    //decomposing list
    val lst = List(1,2,3)
    val lstDescription = lst match {
        case List(_, 2, _) => "List contains 2 as the second item"
        case _ => "Unknown list structure"
    }
    println(lstDescription)

    // if pattern match does not match anything, it will throw a MatchError, which will crash the program.
    // pattern match will try all cases in sequence

    sealed trait Notification
    // the sealed trait is so that the compiler will check that the pattern in matching are fulfilled.
    //If we forget one case:VoiceRecording, the compiler will issue a warning
    case class Email(sender: String, recipient: String, title: String, body: String) extends Notification
    case class Message(sender: String, recipient: String, message:String) extends Notification
    case class VoiceRecording(sender: String, recordingLink: String) extends Notification

    def showNotification(notification: Notification) = {
        notification match {
            case Email(sender, _, title, _) => println(s"You have received an email from $sender with title $title")
            case Message(sender, _, message) => println(s"You have received a message from $sender and the message is $message")
            case VoiceRecording(sender, recordingLink) => println(s"You have received a recording from $sender and the recording link is $recordingLink")
        }
    }
    val email = VoiceRecording("test1", "www.google.com")
    showNotification(email)

    //matching on string
    val str = "Alice is 25 years old"
    val nameAge = str match {
        case s"$name is $age years old" => s"$name's age is $age"
    }
    println(nameAge)

    // pattern guards are used to make cases more specific
    val vip = List("Alice", "kenneth")

    def showNotificationVip(notification: Notification) = {
        notification match {
            case Email(sender, _, title, _) if vip.contains(sender) => println(s"You have received an email from a vip $sender with title $title")
            case Message(sender, _, message) if vip.contains(sender) => println(s"You have received a message from a vip $sender and the message is $message")
            case VoiceRecording(sender, recordingLink) if vip.contains(sender) => println(s"You have received a recording from a vip $sender and the recording link is $recordingLink")
            case other => println("no important message received")
        }
    }
    val vipMessage = Message("kenneth", "test2", "VIP message")
    showNotificationVip(vipMessage)
    showNotificationVip(email)

    // matching on type

    sealed trait Device
    case class Phone() extends Device {
        def screenOff():Unit = println("Turning phone off")
    }
    case class Computer() extends Device {
        def computerOff():Unit = println("Turning computer off")
    }

    def offDevice(device: Device) = {
        device match
            case p:Phone => p.screenOff()
            case computer: Computer => computer.computerOff()
    }
    val phone = Phone()
    offDevice(phone)
}
