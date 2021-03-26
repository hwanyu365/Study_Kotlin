package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Inheritance : Worker {
    override fun run() {
        super.run()

        Triangle().introduce()
        Car().move()

        val heart: Organ = Heart("Heart", "BloodSupplier")
        print("${heart.name}-")
        heart.work()

        val arm: Organ = Arm(99)
        print("${arm.name} (${(arm as Arm).power})-")
        arm.work()

        println("\n\n== Derived class initialization order ==")
        Derived("hwanyu365", "DH")

        println("\n\n== Calling the superclass implementation ==")
        FilledRectangle().draw()
    }
}

/**
 *  All classes in Kotlin have a common superclass Any, that is the default superclass for a class with no supertypes declared:
 * @see Any
 *  - equals()
 *  - hasCode()
 *  - toString()
 *
 *  By default, Kotlin classes are final: they can’t be inherited.
 */

class NoParent // Implicitly inherits from Any

// open
open class Shape constructor(private val name: String = "Shape", private val vertex: Int = 0) {
    fun introduce() {
        println("name=$name, vertex=$vertex")
    }
}

class Triangle constructor(name: String = "Triangle", vertex: Int = 3) : Shape(name, vertex)

// abstract with default params
abstract class Vehicle constructor(private val name: String = "Vehicle", private val cc: Int = 0) {
    init {
        introduce()
    }

    private fun introduce() {
        println("name=$name, cc=$cc")
    }

    abstract fun move()
}

class Car constructor(name: String = "Car", cc: Int = 1600) : Vehicle(name, cc) {
    override fun move() {
        println("Brrrrrr")
    }
}

// abstract without default params
abstract class Organ constructor(val name: String, private val role: String) {
    open val size: Int = 0
    open var position = 100
    abstract var status: Int

    init {
        introduce()
    }

    private fun introduce() {
        println("name=$name, role=$role")
    }

    open fun getOrganName() = name

    abstract fun work()
}

class Heart(name: String, role: String) : Organ(name, role) {
    override var size: Int = 50
    override var position: Int = 100
    override var status: Int = 0

    override fun work() {
        println("beat")
    }
}

open class Arm(val power: Int) : Organ("Arm", "HandBridge") {
    //    override val durability: Int = 9 // can override to same types or val to var
    override var status: Int = 2

    override fun work() {
        println("move hand")
    }

    final override fun getOrganName(): String { //  If you want to prohibit re-overriding, use final
        return "${name}s"
    }

    fun doSomething() {

    }

    fun doSomething(target: Any) { // overloading test

    }
}

open class Base(val name: String) {
    init {
        println("Initializing a base class")
    }

    open val size: Int = name.length.also { println("Initializing size in the base class: $it") }
}

class Derived(name: String, val lastName: String) :
    Base(name.capitalize().also { println("Argument for the base class: $it") }) {
    init {
        println("Initializing a derived class")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
}

open class Rectangle() {
    val borderColor: String
        get() = "black"

    open fun draw() {
        println("Drawing a rectangle")
    }
}

class FilledRectangle: Rectangle() {
    override fun draw() {
        super.draw()
        val filler = Filler()
        filler.drawAndFill()
    }

    // Inside an inner class, accessing the superclass of the outer class is done with the super keyword qualified with the outer class name: super@Outer:
    inner class Filler {
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
        private fun fill() { println("Filling") }
    }
}

interface Material {
    fun consume() {
        println("consume of Material")
    }
}

interface Water {
    fun consume() {
        println("consume of Water")
    }
}

class Something(override var status: Int, name: String, role: String) : Worker, Material, Water, Organ(name, role) {
    override fun work() {
        TODO("Not yet implemented")
    }

    override fun run() {
        TODO("Not yet implemented")
    }

    override fun consume() {
        println("consume of Something")
        super<Water>.consume()
    }
}
