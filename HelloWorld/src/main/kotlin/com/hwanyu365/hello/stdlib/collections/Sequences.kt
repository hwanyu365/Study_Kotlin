package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class Sequences : Worker {
    override fun run() {
        super.run()
        construct()
        sequenceProcessing()
    }

    private fun construct() {
        println("== Construct ==")
        // from elements
//        val numbersSequence = sequenceOf("four", "three", "two", "one")

        // from iterator
        val numbers = listOf("four", "three", "two", "one")
        val numbersSequence = numbers.asSequence()
        println(numbersSequence.joinToString { it })

        // from function
        val numbersSequencesAsFunc = generateSequence(1) { it + 2 }
        println(numbersSequencesAsFunc.take(10).toList())
//        println(numbersSequencesAsFunc.take(10).joinToString { it.toString() })
    }

    private fun sequenceProcessing () {
        println("\n== Sequence processing example ==")

        // by iterable
        val words = "The quick brown fox jumps over the lazy dog".split(" ")
        val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

        println("Lengths of first 4 words longer than 3 chars:")
        println(lengthsList)

        // by sequence
        val wordsSequence = words.asSequence()
        val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

        println("Lengths of first 4 words longer than 3 chars")
        // terminal operation: obtaining the result as a List
        println(lengthsSequence.toList())

        // In this example, the sequence processing takes 18 steps instead of 23 steps for doing the same with lists.
    }
}