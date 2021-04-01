package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class MapSpecificOperations : Worker {
    override fun run() {
        super.run()
        retrieveKeysAndValues()
        filter()
        plusAndMinusOperators()
    }

    private fun plusAndMinusOperators() {
        println("\n== Plus and minus operators ==")
        val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
        println("src=$numbersMap")
        println(numbersMap + Pair("four", 4))
        println(numbersMap + Pair("one", 10))
        println(numbersMap + mapOf("five" to 5, "one" to 11))

        println(numbersMap - "one")
        println(numbersMap - listOf("two", "four"))
        numbersMap -= "two"
        println("src=$numbersMap")
        numbersMap.apply {
            set("one", 100)
            set("three", 200)
        }
        println(numbersMap)
    }

    private fun filter() {
        println("\n== Filter ==")
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
        println(filteredMap)

        val filteredKeysMap = numbersMap.filterKeys { it.endsWith("1") }
        val filteredValuesMap = numbersMap.filterValues { it < 10 }

        println(filteredKeysMap)
        println(filteredValuesMap)
    }

    private fun retrieveKeysAndValues() {
        println("== Retrieve keys and values ==")
        val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
        println(numbersMap.get("one"))
        println(numbersMap["one"])
        println(numbersMap.getOrDefault("four", 10))
        println(numbersMap["five"])               // null
        //numbersMap.getValue("six")      // exception!
    }
}