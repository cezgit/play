package com.elyzar.play.kotlin


class KotlinService {


    private fun addTwoNumbers(num1: Int, num2: Int) = num1 + num2
    private fun addThreeNumbers(num1: Int, num2: Int, num3: Int = 1) = num1 + num2 + num3

    fun sayHello() {
        System.out.println("Kotlin says 'Hello World!'")
        println("4+5 is: ${addTwoNumbers(4, 5)}")
        println("4+5+1 is: ${addThreeNumbers(4, 5)}")

    }

}