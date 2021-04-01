package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class WriteOperations: Worker {
    override fun run() {
        super.run()
        addingElements()
        removingElements()
    }

    private fun addingElements() {
        println("== Adding elements ==")
        val numbers = mutableListOf(1, 2, 3, 4)
        println(numbers)
        numbers.add(5)
        println(numbers)

        numbers.addAll(arrayOf(7, 8))
        println(numbers)
        numbers.addAll(2, setOf(11, 15))
        println(numbers)

        numbers += 5
        println(numbers)
        numbers += listOf(12, 13, 5)
        println(numbers)
    }

    private fun removingElements() {
        println("\n== Removing elements ==")

        val numbers = mutableListOf(-1, 1, 2, 3, 4, 3, -1)
        println(numbers)
        numbers.remove(3)                    // removes the first `3`
//        numbers.removeIf { it == 3 } // removes all '3'
//        numbers.removeAll(listOf(3))
        println(numbers)
        numbers.remove(5)                    // removes nothing
        println(numbers)

        numbers.retainAll { it <= 3 }
        println(numbers)
        numbers -= listOf(-1, 2)
//        numbers.removeAll(listOf(-1, 2))
        println(numbers)
        numbers.clear()
        println(numbers)
    }
}