package com.hwanyu365.hello.types

import com.hwanyu365.hello.Worker

class BasicTypes : Worker {

    override fun run() {
        println("\n\n====== Basic Types ======")
        numbers()
        numbersRepresentationOnJVM()
        operation()
        bitwiseOpt()
        bool()
        characters()
        strings()
        arrays()
    }

    private fun arrays() {
        println("\n== Arrays ==")

        // create
//        val numbers = arrayOf(1, 2, 3, 4, 5)
//        arrays of primitive com.hwanyu365.types without boxing overhead: ByteArray, ShortArray, IntArray, LongArray
        val numbers = IntArray(5) { it * 1 }
        println(numbers)

//        val numbers2 = Array(5) { i -> (i + 1) }
        val numbers2 = Array(5) { i -> (i + 1).toString() }

        // access
        println("${numbers.contentToString()}, size? ${numbers.size}")
        println("${numbers2.contentToString()}, size? ${numbers2.size}")
        printType(numbers[0])
        printType(numbers2[0])

        for (i in numbers.indices) {
            print("${numbers[i]}, ")
        }
        println()
        for (i in numbers) {
            print("$i, ")
        }
        println()
        numbers.forEach {
            print("$it, ")
        }
    }

    private fun strings() {
        println("\n== Strings ==")

//        A raw string is delimited by a triple quote ( ), contains no escaping and can contain newlines and any other characters:"""
        val text = """
    for (c in "foo")
        print(c)
"""
        println(text)

//        To remove leading whitespace from raw strings, use the trimMargin() function:
        val text2 = """
    |@@#Tell me and I forget.
    |@@#Teach me and I remember.
    |@@#Involve me and I learn.
    |@@#(Benjamin Franklin)
    Doosan
    """.trimMargin() // "|" is default

        println(text2)

//        You can use templates both in raw and escaped strings. To insert the character in a raw string (which doesn't support backslash escaping), use the following syntax:$
        val price = """
        ${'$'}9.99"""

        println(price)
    }

    private fun characters() {
        println("\n== Characters ==")

        val c = 'c' // Character literals go in single quotes
        // escape sequences: \ \t \b \n \r \' \" \\ \$
        val aChar: Char = 'a'

        println(aChar)
        print('\n')
        println('\uF100')
        isDecimalDigitChar('1')
        isDecimalDigitChar("1")
        isDecimalDigitChar(1)
        isDecimalDigitChar("one")
    }

    private fun isDecimalDigitChar(obj: Any) {
        when (obj) {
            in '0'..'9' -> {
                println("$obj is Decimal")
            }
            else -> {
                println("$obj is not decimal")
            }
        }
    }

    private fun bool() {
        println("\n== Boolean ==")

        // || && !
        val myTrue = true
        val myFalse = false
        println("$myTrue || $myFalse = ${myTrue || myFalse}")
        println("$myTrue && $myFalse = ${myTrue && myFalse}")
        println("!$myTrue = ${!myTrue}")
    }

    private fun bitwiseOpt() {
        println("== Operation: Bitwise ==")
        // shl shr
        // ushr (unsigned shift right)
        // and or xor inv (inversion)
        val x = 1 shl 2 // 0x0f00
        val y = x and 0x000FF000
        printType(x)
        printType(y)

        val z = 0b0001 // z.inv() = 0b1.....1110 = (-) 0b..._0010 = -2
//        val z = 2 // 0b0010 => z.inv() = 0b1...._1101 = (-) 0b0..._0011 = -3
        printType(z.inv())
    }

    private fun operation() {
        println("\n== Operation ==")

        val x = 5 / 2
        printType(x)
//        println(x == 2.5) // BUILD-TIME ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
        println(x == 2)
    }

    private fun numbersRepresentationOnJVM() {
        println("\n== Numbers representation on the JVM ==")
//        On the JVM platform, numbers are stored as primitive com.hwanyu365.types: int, double, and so on.
//        Exceptions are cases when you create a nullable number reference such as Int? or use generics.
//        In these cases numbers are boxed in Java classes Integer, Double, and so on.

//        ==, !=- equality operators (translated to calls of equals() for non-primitive com.hwanyu365.types)
//        ===, !==- referential equality operators

        val a: Int = 100
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        val b: Int = 10000
        val boxedB: Int? = b
        val anotherBoxedB: Int? = b

//        Note that nullable references to the same number can be different objects:
        println(boxedA === anotherBoxedA) // true
        println(boxedB === anotherBoxedB) // false

//        On the other hand, they are still equal:
        println(a == a) // Prints 'true'
        println(boxedA == anotherBoxedB) // Prints 'true'
        println(boxedB == anotherBoxedB) // Prints 'true'

//        Why the boxed Integers are not the same?
//        This is because Integer only caching values in the range [-128, 127]. the the variable a above is out of the cache range,
//        it will create a new Integer instance for each boxing rather than using a cached value.
//        https://stackoverflow.com/questions/45139381/kotlin-boxed-int-are-not-the-same

        var x: Int? = null
        var y: Int? = 100
        println(x?.equals(y) ?: (y === null))
        println(!(x?.equals(y) ?: (y === null)))
    }

    private fun numbers() {
        println("== Numbers ==")
        val b: Byte = 127
        val s: Short = 32767
        val i: Int = 2147483647
        val l: Long = 922_3372_0368_5477_5807
        val f: Float = 123.5f
        val d: Double = 123.5
        val d2: Double = 123.5e10
        val bl: Boolean = true
        // Unsigned (u): UByte , UShort, UInt, ULong (UL)
        // UByteArray, UShortArray, UIntArray, ULongArray

        // literals
        val h = 0xf0011234
        val bin = 0b1111_0000
//        val bin = 0b1111_0000u

        var x = 1
        val y = 1.1
//        x = y // BUILD-TIME ERROR, type mismatch, there are no implicit widening conversions
        x = y.toInt()

        printType(b)
        printType(s)
        printType(i)
        printType(l)
        printType(f)
        printType(d)
        printType(d2)
        printType(bl)
        printType(h)
        printType(bin)
    }

    private fun printType(obj: Any) {
        when (obj) {
            is Byte -> {
                println("$obj > Byte")
            }
            is Short -> {
                println("$obj > Short")
            }
            is Int -> {
                println("$obj > Int")
            }
            is Long -> {
                println("$obj > Long")
            }
            is Float -> {
                println("$obj > Float")
            }
            is Double -> {
                println("$obj > Double")
            }
            is Boolean -> {
                println("$obj > Boolean")
            }
            is String -> {
                println("$obj > String")
            }
            else -> {
                println("$obj > Unknown type")
            }
        }
    }

}