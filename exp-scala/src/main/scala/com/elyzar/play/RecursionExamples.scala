package com.elyzar.play

class RecursionExamples {

  def main(args : Array[String]): Unit = {

    import scala.annotation.tailrec
    // given a list of Ints, find out their sum using a recursive function

    val myList:List[Int] = 1 to 10000 toList

    def sum(list: List[Int]): Int = list match {
      case Nil => 0
      case head::tail => head + sum(tail)
    }

    //println(sum(myList)) // this throws stack overflow error

    // tail recursive implementation
    def recursiveSum(list: List[Int]): Int = {
      @tailrec
      def sumWithAccumulator(list: List[Int], currentSum: Int): Int = list match {
        case Nil => currentSum
        case x :: xs => sumWithAccumulator(xs, currentSum + x)
      }
      sumWithAccumulator(list, 0)
    }
    println(recursiveSum(myList))


  }

}
