package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Classes:Worker {
    override fun run() {
        val name = "hwanyu365"

        // Kotlin does not have a new keyword.
        InitOrderDemo(37).introduce()
//        InitOrderDemo("hwanyu365", 37).introduce()

        // constructor
        HideDefaultConstructor(name)

        // open, abstract
        Triangle().introduce()
        Car().move()
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

open class Shape constructor(private val name: String = "Shape", private val vertex: Int = 0) {
    fun introduce() {
        println("name=$name, vertex=$vertex")
    }
}

class Triangle constructor(name: String = "Triangle", vertex: Int = 3) : Shape(name, vertex)

abstract class Vehicle constructor(private val name: String = "Vehicle", private val cc: Int = 0) {
    fun introduce() {
        println("name=$name, cc=$cc")
    }

    abstract fun move()
}

class Car constructor(name: String = "Car", cc: Int = 1600) : Vehicle(name, cc) {
    override fun move() {
        println("Brrrrrr")
    }
}