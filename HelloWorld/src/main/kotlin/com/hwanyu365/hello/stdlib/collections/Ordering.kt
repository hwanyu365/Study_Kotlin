package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class Ordering : Worker {
    override fun run() {
        super.run()
        naturalOrder()
        customOrder()
        reverseOrder()
        shuffle()
    }

    private fun shuffle() {
        println("\n== Shuffle ==")
        val list = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        println(list.shuffled())
    }

    private fun reverseOrder() {
        println("\n== Reverse order ==")
        val list = mutableListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")

        val reversedList = list.reversed().toMutableList()// returns a new collection with the copies of the elements.
        reversedList[0] = "wow"
        println(reversedList)
        println(list)

        println()
        val asReversedList = list.asReversed()  // returns a reversed view of the same collection instance
        asReversedList[0] = "wow"
        println(asReversedList)
        println(list)
    }

    private fun naturalOrder() {
        println("\n== Natural order ==")
        val list = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        println(list.sorted())
        println(list.sortedDescending())
    }

    private fun customOrder() {
        println("\n== Custom order ==")
        val versions = listOf(
            Version(1, 10),
            Version(2, 3),
            Version(1, 9),
            Version(0, 9),
            Version(2, 3),
            Version(3, 0),
            Version(1, 8),
        )

        println(versions)
        println(versions.sorted())

        val dumpComparator = Comparator { v1: Version, v2: Version ->
            when {
                v1.minor != v2.minor -> {
                    v1.minor - v2.minor
                }
                else -> {
                    0
                }
            }
        }
        println(versions.sortedWith(dumpComparator))
    }
}

data class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        return when {
            major != other.major -> {
                major - other.major
            }
            minor != other.minor -> {
                minor - other.minor
            }
            else -> {
                0
            }
        }
    }
}