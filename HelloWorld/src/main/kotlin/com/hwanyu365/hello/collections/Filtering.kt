package com.hwanyu365.hello.collections

import com.hwanyu365.hello.Worker

class Filtering:Worker {

    override fun run() {
        super.run()
        filterByPredicate()
        filterPartition()
        predicates()
    }

    private fun predicates() {
        println("\n== Predicate ==")
        val numbers = listOf("one", "two", "three", "four")

        println(numbers.any { it.endsWith("e") })
        println(numbers.none { it.endsWith("a") })
        println(numbers.all { it.endsWith("e") })

        println(emptyList<Int>().all { it > 5 })   // vacuous truth

        println()
        val empty = emptyList<String>()

        println(numbers.any())
        println(empty.any())

        // opposite
        println(numbers.none())
        println(empty.none())
    }

    private fun filterPartition() {
        println("\n== Partition ==")
        val numbers = listOf("one", "two", "three", "four")
        val (match, rest) = numbers.partition { it.length > 3 }
        println(match)
        println(rest)
    }

    private fun filterByPredicate() {
        println("== Filter by predicate ==")
        val numbers = listOf("one", "two", "three", "four")
        val longerThan3 = numbers.filter { it.length > 3 }
        println(longerThan3)

        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
        println(filteredMap)

        val filteredIdx = numbers.filterIndexed { idx, s -> (idx != 0) && (s.length < 5)  }
        val filteredNot = numbers.filterNot { it.length <= 3 }
        println(filteredIdx)
        println(filteredNot)

        val something = listOf(null, 1, "two", 3.0, "four")
        println("All String elements in upper case:")
        something.filterIsInstance<String>().forEach {
            println(it.toUpperCase())
        }
    }

}