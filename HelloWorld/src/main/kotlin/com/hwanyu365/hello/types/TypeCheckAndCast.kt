package com.hwanyu365.hello.types

import com.hwanyu365.hello.Worker

class TypeCheckAndCast : Worker {

    override fun run() {
        println("\n\n====== Type checks and casts ======")
        isAndNotIs()
        smartCasts()
        safeAndUnsafeCast()
    }

    private fun safeAndUnsafeCast() {
        println("\n== Safe and Unsafe cast ==")

        // unsafe cast
//        val x:String? = null
//        val y: String =  x as String // throws an NPE
//        val y: String? =  x as String? // To make such code correct for null values, use the nullable type on the right hand side of the cast

//        val x = "null"
//        val y: Int? =  x as Int? // throws an ClassCastException

        // safe (nullable) cast ('as?')
//        val m: Int = 10
        val m: Any = "null"
        val n: Int? =
            m as? Int // Note that despite the fact that the right-hand side of as? is a non-null type String, the result of the cast is nullable.
        println(n)
    }

    private fun smartCasts() {
        println("\n== Smart casts ==")

        var x: Any = IntArray(10) { it }
        when (x) {
            is Int -> println("Increased ${x + 1}")
            is String -> println("Length? ${x.length + 1}")
            is IntArray -> println("Sum of arrays? ${x.sum()}")
        }
    }

    private fun isAndNotIs() {
        println("\n== is and !is operator ==")

        var a: Any = "ShapeChanger"
        println("Is the a String? ${a is String}")
        println("Is not the a String? ${a !is String}")
    }
}