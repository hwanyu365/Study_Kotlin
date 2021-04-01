package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class Grouping:Worker {
    override fun run() {
        super.run()
        val numbers = listOf("one", "two", "three", "four", "five")
        println(numbers.groupBy { it.first().toUpperCase() }) // return Map<K, List<T>>
        println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))

        println(numbers.groupingBy { it.first().toUpperCase() }.eachCount())
    }
}