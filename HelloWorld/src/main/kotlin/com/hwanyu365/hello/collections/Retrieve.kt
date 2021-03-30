package com.hwanyu365.hello.collections

import com.hwanyu365.hello.Worker

class Retrieve : Worker {
    override fun run() {
        super.run()
        slice()
        takeAndDrop()
    }

    private fun takeAndDrop() {
        println("\n== Take and drop ==")
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.take(2))
        println(numbers.takeLast(2))
        println(numbers.drop(2)) // == takeLast(size-n)
        println(numbers.dropLast(2)) // == take(size-n)
    }

    private fun slice() {
        println("== Slice ==")
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.slice(1..4))
        println(numbers.slice(3 downTo 1))
        println(numbers.slice(numbers.indices step 2))
    }

}