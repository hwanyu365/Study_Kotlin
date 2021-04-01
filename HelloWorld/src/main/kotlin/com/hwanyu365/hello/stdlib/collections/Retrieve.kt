package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker
import kotlin.random.Random

class Retrieve : Worker {
    override fun run() {
        super.run()
        slice()
        takeAndDrop()
        chunked()
        windowed()
        retrieveByPosition()
    }

    private fun retrieveByPosition() {
        println("\n== Retrieve by position ==")

        val sortedNumbers = sortedSetOf("one", "two", "three", "four", "five")
        println(sortedNumbers.elementAt(3))
        println(sortedNumbers.elementAtOrNull(10))
        println(sortedNumbers.elementAtOrElse(10) { idx -> "The $idx is out of bounds!" })

        println("first=${sortedNumbers.first()}, last=${sortedNumbers.last()}")
        println("first=${sortedNumbers.first { it.startsWith("o") }}")
        println("firstOrNull=${sortedNumbers.firstOrNull { it.startsWith("x") }}")

//        println("firstOrNull=${sortedNumbers.first { it.startsWith("x") }}") // NoSuchElementException is occurred
        println("find=${sortedNumbers.find { it.startsWith("x") }}") // use find() instead of firstOrNull, use findLast() instead of lastOrNull

        println("random=${sortedNumbers.random()}")
    }

    private fun windowed() {
        println("\n== Windowed ==")
        val numbers = listOf("one", "two", "three", "four", "five")
        println(numbers.windowed(3)) // returns a list of element ranges that you would see if you were looking at the collection through a sliding window of the given size

        val numbersAsInt = (0..10).toList()
        println(numbersAsInt.windowed(3, step = 2, partialWindows = true))
        println(numbersAsInt.windowed(3, step = 2, partialWindows = true) { it.sum() })

        println(numbersAsInt.windowed(3))
        println(numbersAsInt.windowed(3) { it.sum() })
    }

    private fun chunked() {
        println("\n== Chunked ==")
        val numbers = List(10) { it } // (0..10).toList()
        println("chunked = ${numbers.chunked(5)}") // To break a collection onto parts of a given size, Return List of Lists
        println(numbers.chunked(3) { it.sum() })  // `it` is a chunk of the original collection

        val (p1, p2) = numbers.partition { it % 2 == 0 } // Return match and rest Lists
        println("partition = $p1, $p2")
    }

    private fun takeAndDrop() {
        println("\n== Take and drop ==")
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println("$numbers.take(2) = ${numbers.take(2)}")
        println("$numbers.takeLast(2) = ${numbers.takeLast(2)}")
        println("$numbers.drop(2) = ${numbers.drop(2)}") // == takeLast(size-n)
        println("$numbers.dropLast(2) = ${numbers.dropLast(2)}") // == take(size-n)

        println()
        println("$numbers.takeWhile(...) = ${numbers.takeWhile { !it.startsWith('f') }}")
        println("$numbers.takeLastWhile(...) = ${numbers.takeLastWhile { it != "three" }}")
        println("$numbers.dropWhile(...) = ${numbers.dropWhile { it.length == 3 }}")
        println("$numbers.dropLastWhile(...) = ${numbers.dropLastWhile { it.contains('i') }}")
//        println("$numbers.takeIf(..) = ${numbers.takeIf { it.length > 3 }}")
    }

    private fun slice() {
        println("== Slice ==")
        val numbers = mutableListOf("one", "two", "three", "four", "five", "six")
        println(numbers.slice(1..4))
        println(numbers.slice(3 downTo 1))
        println(numbers.slice(numbers.indices step 2))
        println(numbers.subList(1, 4))
    }
}