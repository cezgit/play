package com.elyzar.play

object FunctionExamples {
  
  def main(args : Array[String]) {

    def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
    println( "Hello World!" )
    println("concat arguments = " + foo(args))

    println("-------------------- functions --------------------")

    val doubleit = (x: Int) => x * 2
    def map[A, B](f: (A) => B, list: Seq[A]): Seq[B] = {
      for { x <- list } yield f(x)
    }

    map(doubleit, Seq(1, 2, 3))

    def timer[A](codeBlock: => A) = {
      val startTime = System.nanoTime()
      val result = codeBlock
      val endTime = System.nanoTime()
      val delta = endTime - startTime
      (result, delta/1000000d)
    }
    val (result, time) = timer(println("Hello!"))

    def readFile(filename: String) =
      println(io.Source.fromFile(filename).getLines().size)
    timer(readFile("/etc/passwd"))

    println("-------------------- param groups --------------------")

    def sum(a: Int)(b: Int)(c: Int) = a + b + c
    sum(1)(2)(3)

    def plus(a: Int)(b: Int) = a + b
    def plusTwo = plus(2)(_)
    println(plusTwo(3))


    def whilst(testCondition: => Boolean)(codeBlock: => Unit) = {
      while(testCondition) {
        codeBlock
      }
    }

    var i = 1
    whilst(i <= 5) {
      println(i)
      i += 1
    }

    def ifBothTrue(cond1: => Boolean)(cond2: => Boolean)(codeBlock: => Unit) = {
      if(cond1 && cond2) {
        codeBlock
      }
    }

    val age = 15
    val numAccidents = 0
    val cond1: Boolean = age > 16
    val cond2: Boolean = numAccidents == 0
    ifBothTrue(cond1)(cond2)(println("Great!"))

    println("-------------------- implicit params --------------------")

    def printIntIfTrue(a: Int)(implicit b: Boolean) = if(b) println(a)
    printIntIfTrue(3)(true)

    implicit val boo = true
    printIntIfTrue(33)

    println("-------------------- curried functions --------------------")

    def add(x: Int, y: Int) = x + y
    val addFunction = add _
    println(addFunction(2, 3))

    // calling the curried method on the add function instance creates a new function that has two parameter groups
    val addCurried = (add _).curried
    println(addCurried(1)(2))

    // partially applied function
    val addCurriedTwo = addCurried(2)
    println(addCurriedTwo(3))

    def wrap(prefix: String, html: String, suffix: String) = {
      prefix + html + suffix
    }
    val wrapWithDiv = wrap("<div>", _: String, "</div>")
    println(wrapWithDiv("Hello, world"))
  }

}
