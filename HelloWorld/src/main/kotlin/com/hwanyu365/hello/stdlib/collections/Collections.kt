package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker
import com.hwanyu365.hello.util.log
import java.util.*

internal class Collections : Worker {

    /**
     * Iterable
     * MutableIterable: Iterable
     *
     * Collection:Iterable
     * MutableCollection:Collection, MutableIterable
     *
     * List
     *  - List stores elements in a specified order and provides indexed access to them
     *  - List elements (including nulls) can duplicate
     *  - List: Collection
     *  - MutableList: List, MutableCollection
     * Set
     *  - Set stores unique elements, their order is generally undefined
     *  - {null} elements are unique as well: a {Set} can contain only one null
     *  - Set: Collection
     *  - MutableSet: Set, MutableCollection
     * Map
     *  - Map stores key-value pairs (or entries ); keys are unique, but different keys can be paired with equal values.
     *  - Map
     *  - MutableMap: Map
     */
    override fun run() {
        super.run()
        emptyCollections()
        constructFromElements()
        initializerFunctionsForList()
        copyCollections()
        invokeFunctions()
        iterators()
        RangesAndProgressions().run()
        Sequences().run()
        Operations().run()
        Filtering().run()
        Grouping().run()
        Retrieve().run()
        Ordering().run()
        WriteOperations().run()
        SetSpecificOperations().run()
        MapSpecificOperations().run()
    }

    private fun iterators() {
        println("\n== List iterators ==")
        val list = listOf("one", "two", "three", "four")
        val listIterator = list.listIterator()
        while (listIterator.hasNext()) listIterator.next()
        println("Iterating backwards:")
        while (listIterator.hasPrevious()) {
            print("Index: ${listIterator.previousIndex()}")
            println(", value: ${listIterator.previous()}")
        }

        println("\n== Mutable iterators ==")
        val numbers = mutableListOf("one", "four", "four")
        val mutableListIterator = numbers.listIterator()

        mutableListIterator.next()
        mutableListIterator.add("two")
        mutableListIterator.next()
        mutableListIterator.set("three")
        println(numbers)
    }

    private fun invokeFunctions() {
        println("\n== Invoke functions on other collections ==")
        val list = List(10) { it }
        val filteredList = mutableListOf<Int>()
        list.filterTo(filteredList) { it % 2 != 0 }
        list.log("src")
        filteredList.log("odd")
        list.filter { (it != 0) && (it % 2 == 0) }.log("even")

        val map = mapOf("banana" to "yellow", "apple" to "Red", "grape" to "green")
        map.log("map")
    }

    private fun copyCollections() {
        println("\n== Copy ==")
        val list = List(5) { it * 2 + 1 }
        list.log("source")

        val readableCopiedList = list.toList()
        readableCopiedList.log("readableCopiedList")

        val copiedList = list.toMutableList()
        copiedList.add(7)
        copiedList.log("copiedList")
    }

    private fun initializerFunctionsForList() {
        println("\n== Initializer functions for lists ==")
        List(10) { it }.log()
        List(10) { it * 2 + 1 }.log()
        List(10) { (it + 1) * 2 }.log()

        val linkedList = LinkedList(listOf("one", "two", "three"))
        linkedList.add("four")
        linkedList.log()
    }

    private fun constructFromElements() {
        println("\n== Construct from elements ==")
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf(3, 4, 5)

        println("[${set1.joinToString { "$it" }}].retainAll([${set2.joinToString { "$it" }}])? ${set1.retainAll(set2)}")
        println("set1=[${set1.joinToString { "$it" }}], set2=[${set2.joinToString { "$it" }}]")

        val words = "A long time ago in a galaxy far far away".split(" ")
        val shortWords = mutableListOf<String>()
        words.getShortWords(shortWords, 3)
        println("shortWords? ${shortWords.joinToString { it }}")

        val map = mapOf("name" to "hwanyu365", 6 to "jake")
        println("name:${map["name"]}, 6:${map[6]}")
    }

    private fun List<String>.getShortWords(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }
        val articles = setOf("a", "A", "an", "An", "the", "The")
        shortWords -= articles
    }

    private fun emptyCollections() {
        println("== Empty collections ==")
        val emptyList = emptyList<Int>() // Not mutable
        println("emptyList.isEmpty()? ${emptyList.isEmpty()}")
    }
}