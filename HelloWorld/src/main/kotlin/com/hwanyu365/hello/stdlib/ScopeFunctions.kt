package com.hwanyu365.hello.stdlib

import com.hwanyu365.hello.Worker
import kotlin.random.Random

class ScopeFunctions : Worker {

    /**
     * Because the scope functions are all quite similar in nature, it's important to understand the differences between them.
     * There are two main differences between each scope function:
     * - The way to refer to the context object
     * - The return value.
     */
    override fun run() {
        super.run()

        // {let} can be used to invoke one or more functions on results of call chains
        scopeFuncLet()      // it,      lambda result
        // A non-extension function: the context object is passed as an argument, {with} can be read as 'with this object, do the following.'
        scopeFuncWith()     // this,    lambda result
        // run does the same as with but invokes as let- as an extension function of the context object.
        scopeFuncRun()      // this,    lambda result
        // Use apply for code blocks that don't return a value and mainly operate on the members of the receiver object.
        scopeFuncApply()    // this,    context
        // also is good for performing some actions that take the context object as an argument.
        scopeFuncAlso()     // it,      context

        takeIfAndTakeUnless()
    }

    private fun takeIfAndTakeUnless() {
        println("\n== takeIf and takeUnless ==")
        val number = Random.nextInt()
        println("even=${number.takeIf { it % 2 == 0 }}, odd=${number.takeUnless { it % 2 == 0 }}")

        fun displaySubstringPosition(input: String, sub: String) {
            input.indexOf(sub).takeIf { it >= 0 }?.let {
                println("The substring $sub is found in $input.")
                println("Its start position is $it.")
            }
//            val index = input.indexOf(sub)
//            if (index >= 0) {
//                println("The substring $sub is found in $input.")
//                println("Its start position is $index.")
//            }
        }

        displaySubstringPosition("010000011", "11")
        displaySubstringPosition("010000011", "12")
    }

    private fun scopeFuncAlso() {
        println("\n== Also ==")
        val numbers = (0..10).toMutableList()
        numbers.also { println("numbers=$it") }.add(365)
        println("numbers=$numbers")
    }

    private fun scopeFuncApply() {
        println("\n== Apply ==")
        val alice = Person("Alice", 25, "NewYork").run {
            age = 30
            location = "Korea"
            println(this)
            this
        }

        val alice2 = Person("Alice", 25, "NewYork").apply {
            age = 30
            location = "Korea"
            println(this)
        }

        println(alice)
        println(alice2)
    }

    // run is useful when your lambda contains both the object initialization and the computation of the return value.
    private fun scopeFuncRun() {
        println("\n== Run ==")
        val numbers = mutableSetOf("one" to 1, "two" to 2, "three" to 3)
        numbers += Pair("four", 4)
        numbers.run { println(this) }
        numbers.let { println(it) }

        // Non-extension run lets you execute a block of several statements where an expression is required.
        val hexNumberRegex = run {
            val digits = "0-9"
            val hexDigits = "A-Fa-f"
            val sign = "+-"

            Regex("[$sign]?[$digits$hexDigits]+")
        }

        for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number, +ac")) {
            println(match.value)
        }
    }

    private fun scopeFuncWith() {
        println("\n== With ==")
        val p = Person("Alice", 25, "NewYork")
        with(p) {
            println(this)
            moveTo("Seoul")
            incrementAge()
            println(this)
        }
    }

    private fun scopeFuncLet() {
        println("== Let ==")
        Person("Alice", 25, "NewYork").let {
            println(it)
            it.incrementAge()
            it.moveTo("London")
            println(it)
        }

        val numbers = mutableListOf("one", "two", "three", "four", "five")
//        val resultList = numbers.map { it.length }.filter { it > 3 }
//        println(resultList)

//        numbers.map { it.length }.filter { it > 3 }.let {
//            println(it)
//        }

        numbers.map { it.length }.filter { it > 3 }.let(::println)

        // To perform actions on a non-null object, use the safe call operator ?.
        val str: String? = "Hello"
        val length = str?.let {
            println("let() called")
            it.length
        }

        val modifiedFirstItem = numbers.first().let { firstItem ->
            println("The first item of the list is '$firstItem'")
            if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
        }.toUpperCase()
        println("First item after modifications: '$modifiedFirstItem'")
    }

    data class Person(val name: String, var age: Int, var location: String) {
        fun incrementAge() {
            age++
        }

        fun moveTo(loc: String) {
            location = loc
        }
    }
}