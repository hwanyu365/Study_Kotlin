package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Classes : Worker {
    override fun run() {
        super.run()
        val name = "hwanyu365"

        // Kotlin does not have a new keyword.
        InitOrderDemo(37).introduce()
//        InitOrderDemo("hwanyu365", 37).introduce()

        // constructor
        HideDefaultConstructor(name)
    }
}

/**
 * The class declaration consists of belows:
 *   - class name
 *   - class header (optional)
 *      - type parameters
 *      - primary constructor (the keyword can be omitted if it not have any annotations or visibility modifiers)
 *      - etc
 *   - class body (optional)
 *   - curly braces (can be omitted if there is no both header and body)
 */
class Empty

/**
 * There are four visibility modifiers in Kotlin:
 * - private
 * - protected
 * - internal
 * - public (default)
 *
 * Packages
 * - private
 * - internal
 * - public
 *
 * Classes and interfaces
 * - private
 * - protected
 * - internal
 * - public
 *
 * Constructor
 * - private
 * - internal
 * - public
 *
 * Local
 * - cannot have
 */

class InitOrderDemo private constructor(private val name: String, var age: Int) { // trailing comma
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    fun introduce() {
        println("Name: $name, Age: $age")
    }

    // If the class has a primary constructor, each secondary constructor needs to delegate to the primary constructor,
    // either directly or indirectly through another secondary constructor(s).
    constructor(age: Int) : this("hwanyu365", age) {
        println("initialized through secondary constructor")
    }
}

class HideDefaultConstructor private constructor() {
    constructor(name: String) : this() {
        println("HideDefaultConstructor.name is $name")
    }
}