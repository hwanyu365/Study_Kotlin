package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

/**
 * If the value of a read-only property is known at the compile time, mark it as a compile time constant using the const modifier.
 * Such properties need to fulfil the following requirements:
 * - Top-level, or member of an object declaration or a companion object.
 * - Initialized with a value of type String or a primitive type
 * - No custom getter
 */
const val NICK: String = "hwanyu365"

class Properties() : Worker {
    override fun run() {
        super.run()

        println("isFirst? $isFirst")
        println("simple is $counter")
        isFirst = false
        println("isFirst? $isFirst")

        if (!this::lateInitialized.isInitialized) {
            ready()
        }
        lateInitialized.move()
    }

    /**
     * Properties in Kotlin classes can be declared either as mutable using the {var} keyword, or as read-only using the {val} keyword.
     *
     * full syntax:
     * var <propertyName>[: <PropertyType>] [= <property_initializer>]
     *     [<getter>]
     *     [<setter>]
     *
     * Property type is optional if it can be inferred from the initializer (or from the getter return type, as shown below).
     *
     * On JVM: Access to private properties with default getters and setters is optimized to avoid function call overhead.
     */

    var initialized = 1 // has type Int, default getter and setter
    // var allByDefault // ERROR: explicit initializer required, default getter and setter implied

    val counter: Int? // has type Int, default getter, must be initialized in constructor or init()
        get() = if (field !== null) field.inc() else null
    val inferredType = 1 // has type Int and a default getter
    init {
        counter = 0
    }

//    You can omit the property type if it can be inferred from the getter:
    var isFirst: Boolean = false
    get() = (counter == 1)

    var setterVisibility: String = "abc"
        private set // the setter is private and has the default implementation
//        @Inject set // annotate the setter with Inject

    lateinit var lateInitialized: Vehicle

    fun ready() {
        lateInitialized = Car()
    }

}