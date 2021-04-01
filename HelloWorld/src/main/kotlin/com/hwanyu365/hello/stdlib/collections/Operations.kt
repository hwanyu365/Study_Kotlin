package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class Operations : Worker {
    override fun run() {
        super.run()
        mapOp()
        zipOp()
        associateOp()
        flattenOp()
        plusAndMinusOperators()
        aggregateOperations()
        foldAndReduce()
    }

    private fun foldAndReduce() {
        println("\n== Fold and reduce ==")

        val numbers = listOf(5, 2, 10, 4)
        val sum =
            numbers.reduce { acc, element -> acc + element } // the first step of reduce() uses the first and the second elements as operation arguments on the first step
        println("numbers.reduce=$sum")

        val sumDoubled = numbers.fold(0) { acc, element -> acc + element * 2 }
        val sumDoubledReduce =
            numbers.reduce { acc, element -> acc + element * 2 } // incorrect: the first element isn't doubled in the result
        println("numbers.fold=$sumDoubled, number.reduce=$sumDoubledReduce")

        val sumDoubledRight = numbers.foldRight(0) { element, acc -> acc + element * 2 }
        val sumDoubledReduceRight = numbers.reduceRight { element, acc -> acc + element * 2 } // incorrect: the first element isn't doubled in the result
        println("numbers.foldRight=$sumDoubledRight, number.reduceRight=$sumDoubledReduceRight")

        val sumEven = numbers.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum }
        val sumEvenRight = numbers.foldRightIndexed(0) { idx, element, sum -> if (idx % 2 == 0) sum + element else sum }
        println("numbers.foldIndexed=$sumEven, number.foldRightIndexed=$sumEvenRight")

        val runningReduceSum = numbers.runningReduce { sum, item -> sum + item }
        val runningFoldSum = numbers.runningFold(10) { sum, item -> sum + item }
        println("numbers.runningReduce=$runningReduceSum, number.runningFold=$runningFoldSum")
    }

    private fun aggregateOperations() {
        println("\n== Aggregate operations ==")
        val numbers = (0..10).toList()
        println("count=${numbers.count()}, min=${numbers.minOrNull()}, max=${numbers.maxOrNull()}, sum=${numbers.sum()}, avg=${numbers.average()}")
        println("sumOf=${numbers.sumOf { it % 5 }}")
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

    data class FullName(val firstName: String, val lastName: String)

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