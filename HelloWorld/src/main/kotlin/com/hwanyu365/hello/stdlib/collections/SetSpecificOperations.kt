package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class SetSpecificOperations:Worker {
    override fun run() {
        super.run()
        val numbers = mutableSetOf("one", "two", "three")
        println(numbers)
        println(numbers union setOf("four", "five"))
        println(setOf("four", "five") union numbers)

        println(numbers intersect setOf("two", "one"))
        println(numbers subtract setOf("three", "four"))
        println(numbers)
        numbers -= setOf("three", "four")
        println(numbers)
        println(numbers subtract setOf("four", "three")) // same output
    }
}