package com.hwanyu365.hello.functions

import com.hwanyu365.hello.Worker

class Lambdas : Worker {
    override fun run() {
        super.run()
        highOrderFunctions()
        lambdaClosure()
        withReceiver()
    }

    //    A lambda expression or anonymous function (as well as a local function and an object expression) can access its closure,
//    which includes the variables declared in the outer scope. The variables captured in the closure can be modified in the lambda:
    private fun lambdaClosure() {
        println("\n== Closures ==")
        val list = listOf(1, 2, 3, 4)
        var sum = 0
        list.filter { it > 2 }.forEach { sum += it }
        println("sum= $sum")
    }

    // Function literals with receiver, A.(B) -> C
    private val sumVal: Int.(Int) -> Int = { other -> plus(other) }
    private val increase = { i: Int -> i + 1 }
    private fun withReceiver() {
        println("\n== Function literals with receiver ==")
        println(6.sumVal(5))
        println("${increase(6)} , ${increase.invoke(9)}")
    }

    // A higher-order function is a function that takes functions as parameters, or returns a function.
    private fun highOrderFunctions() {
        println("== High-order functions ==")

        printResult(::sum)
        printResult(::subtract)
    }

    private fun printResult(f: (Int, Int) -> Int) {
        println(f(10, 5))
    }

    private fun sum(a: Int, b: Int) = a + b
    private fun subtract(a: Int, b: Int) = a - b
}