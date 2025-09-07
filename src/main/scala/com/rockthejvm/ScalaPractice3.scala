package com.rockthejvm

object ScalaPractice3 extends App {
    sealed trait Membership
    case object Junior extends Membership
    case object Regular extends Membership
    case object Senior extends Membership

    case class Member(id:String, name:String, membership: Membership, borrowedItems: List[String])

    case class Library(map: Map[String, Member]) {
        def getBorrowingLimit(membership: Membership): Int = {
            membership match {
                case Junior => 1
                case Regular => 2
                case Senior => 3
            }
        }

        //canBorrow(takes in a memberld and a item count, which checks the member against the map, and calls getBorrowing Limit
        // for its limit, and returns a boolean if it can borrow.)
        def canBorrow(memberId: String, itemCount: Int): Boolean = {
            val valid = map.get(memberId).
                fold(false)(member => member.borrowedItems.length + itemCount <= getBorrowingLimit(member.membership))
            valid
        }

        //borrowtem(takes in a memberld(String) and an itemid(String) and return an Either [String, Library)
        //which either returns an error if we cannot borrow or a library with the Member item updated to include this new item borrowed.
        def borrowItem(memberId: String, itemId: String): Either[String, Library] = {
            val validBorrow = canBorrow(memberId, 1)
            if (validBorrow) {
                val member = map(memberId) // throws a no such element exception if no such key exist
                val updatedMember = member.copy(borrowedItems = member.borrowedItems :+ itemId)
                val updatedLibrary = this.copy(map = map + (memberId -> updatedMember))
                Right(updatedLibrary)
            } else {
                Left("User can no longer borrow the item")
            }
        }
    }

    //  Testing
    val emptyLibrary = Library(Map.empty)

    val john = Member("1", "John", Junior, Nil)
    val alice = Member("2", "Alice", Regular, List("Book A"))
    val senior = Member("3", "Bob", Senior, List("Book X", "Book Y"))

    val libraryWithMembers = emptyLibrary.copy(
        map = Map(
            john.id -> john,
            alice.id -> alice,
            senior.id -> senior
        )
    )

    println("Initial library: " + libraryWithMembers)

    // John borrows one book
    val step1 = libraryWithMembers.borrowItem("1", "Book B")
    println("After John borrows: " + step1)

    // Alice (already has 1 book, limit 2) borrows another
    val step2 = step1.map(_.borrowItem("2", "Book C"))
    println("After Alice borrows: " + step2)

    // Bob (already has 2, limit 3) borrows another
    val step3 = step2.flatMap(_.map(_.borrowItem("3", "Book Z")))
    println("After Bob borrows: " + step3)

    // John tries to borrow again (limit 1, already borrowed)
    val fail = step1.map(_.borrowItem("1", "Book D"))
    println("John tries again: " + fail)

}

//5. In the Library, people are allowed to borrow an item only if they are members. Different membership categories are
// allowed to borrow different numbers of items, for example a junior member can borrow a certain number, a "regular"
// member can borrow a different number, a  "senior citizen" member can borrow another different number. Implement
// functionality to support this for your library's customers.

//borrowtem(takes in a memberld(String) and an itemid(String) and return an Either [String, Library)
//which either returns an error if we cannot borrow or a library with the Member item updated to include this new item borrowed.