package com.hwanyu365.hello.collections

import com.hwanyu365.hello.Worker

class Operations : Worker {
    override fun run() {
        super.run()
        mapOp()
        zipOp()
        associateOp()
        flattenOp()
        plusAndMinusOperators()
    }

    private fun plusAndMinusOperators() {
        println("\n== Plus and minus operators ==")
        val numbers = listOf("one", "two", "three", "four")

        val plusList = numbers + "five"
        val minusList = numbers - listOf("three", "four")
        println("$numbers + five = $plusList")
        println("$numbers - ${listOf("three", "four")} = $minusList")
    }

    // If you operate nested collections, you may find the standard library functions that provide flat access to nested collection elements useful.
    private fun flattenOp() {
        println("\n== Transformation operations - Flatten ==")

        // flatten()
        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println(numberSets.flatten())

        // flatMap()
        val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight"))
        )
        println(containers.flatMap { it.values }) // behaves as a subsequent call of map()
        println((containers.map { it.values }).flatten())
    }
    data class StringContainer(val values: List<String>)

    private fun associateOp() {
        println("\n== Transformation operations - Associate ==")

        val color = listOf("brown", "gray", "blue")
        println(color.associateWith { it.length }) // return Map<K, V>
        println(color.associateBy { it.length })
        println(color.associateBy { it.first().toUpperCase() }) // return Map<K, V>
        println(color.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))

        val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell", "Jack Brown")
        println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })
    }

    data class FullName (val firstName: String, val lastName: String)
    private fun parseFullName(fullName: String): FullName {
        val nameParts = fullName.split(" ")
        if (nameParts.size == 2) {
            return FullName(nameParts[0], nameParts[1])
        } else throw Exception("Wrong name format")
    }

    private fun zipOp() {
        println("\n== Transformation operations - Zip ==")

        val color = listOf("brown", "gray")
        val animal = listOf("bear", "wolf", "fox")
        println(color zip animal) // returns List of Pair objects

        val zipped = color zip animal
        println(zipped.unzip())
    }

    private fun mapOp() {
        println("== Transformation operations - Map ==")

        val list = List(10) { it }
        println(list.map { it * 2 })
        println(list.mapIndexedNotNull { idx, it -> if (it % 2 == 0) null else "$idx / ${it * 2}" })
    }
}