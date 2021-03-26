package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class DataClasses : Worker {
    override fun run() {
        super.run()
        val jake = Person("Jake", 37)
        println(jake.toString())

        val jakeClone = jake.copy(name = "JakeClone")
        println(jakeClone.toString())

        val jakeClone2 = jake.copy()
        jakeClone2.nick = "JJ"
        println("isEquals? ${jake == jakeClone} vs ${jake == jakeClone2}")

        // Data classes and destructuring declarations
        val (name, age) = jakeClone
        println("name=$name, age=$age")

        val jakeAsPair = Pair("Jake", 37)
        val jakeAsTriple = Triple("Jake", 37, "JJ")
        println("jakeAsPair=$jakeAsPair, jakeAsTriple=$jakeAsTriple")
        val jakeAsPairCone = jakeAsPair.copy(first = "JakeClone")
        val jakeAsPairCone2 = jakeAsPair.copy()
        println("isEquals? ${jakeAsPair == jakeAsPairCone} vs ${jakeAsPair == jakeAsPairCone2}")
    }
}

/**
 * The compiler automatically derives the following members from all properties declared in the primary constructor:
 * - equals()/ hashCode() pair
 * - toString() of the form "User(name=John, age=42)"
 * - componentN() functions corresponding to the properties in their order of declaration.
 * - copy() function (see below).
 *
 * To ensure consistency and meaningful behavior of the generated code, data classes have to fulfill the following requirements:
 * - The primary constructor needs to have at least one parameter.
 * - All primary constructor parameters need to be marked as val or var.
 * - Data classes cannot be abstract, open, sealed or inner.
 */
data class Person(val name: String, val age: Int) {
    var nick: String = "Unknown"
}