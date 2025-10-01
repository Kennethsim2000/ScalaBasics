package com.rockthejvm

object ScalaPractice10 extends App {
    //Ex3: Look at the documentation for the built-in List (+A) class. Use the foldLeft method to implement reverse on
    // an instance of this class. Can you add this capability to the MyList (A) class?

    val lst = List(1,2,3)
    val reversedList = lst.foldLeft(List.empty: List[Int])((currList: List[Int], elem: Int) => elem :: currList)
    val reversedStr = reversedList.mkString(",")
    println(reversedStr)

    //Ex4: Binary tree is a type that can also be defined easily using a recursive ADT: A tree is a leaf node, which contains a value
    //To an internal node, which contains a left node and a right node
    //Implement this type, and provide a fold method for it. Use the fold method to implement length, which returns the number of values (leaf nodes) in the tree

    sealed trait Node {
        def fold: Int
    }
    case class NormalNode(var value: Int, leftNode: Node, rightNode: Node) extends Node {

        override def fold: Int = 1 + leftNode.fold + rightNode.fold
    }
    case class LeafNode(var value: Int) extends Node {
        override def fold: Int = 1
    }

    val tree = NormalNode(2, NormalNode(3, LeafNode(4), LeafNode(5)), NormalNode(4, LeafNode(6), LeafNode(8)))
    println(tree.fold)
}

