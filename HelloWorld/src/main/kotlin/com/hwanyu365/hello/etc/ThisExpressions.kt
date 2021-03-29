package com.hwanyu365.hello.etc

import com.hwanyu365.hello.Worker

class ThisExpressions : Worker {
    override fun run() {
        super.run()
        implicitTest()
    }

    /**
     * To access this from an outer scope (a class, extension function, or labeled function literal with receiver) you write this@label,
     * where @label is a label on the scope this is meant to be from:
     */
    class A { // implicit label @A
        inner class B { // implicit label @B
            fun Int.foo() { // implicit label @foo
                val a = this@A // A's this
                val b = this@B // B's this

                val c = this // foo()'s receiver, an Int
                val c1 = this@foo // foo()'s receiver, an Int
                println("foo is cllaed. $a, $b, $c, $c1")

                val funLit = lambda@ fun String.() {
                    val d = this // funLit's receiver
                }

                val funLit2 = { s: String ->
                    // foo()'s receiver, since enclosing lambda expression
                    // doesn't have any receiver
                    val d1 = this
                }
            }
        }
    }
}

fun implicitTest() {
    val c = C()
    c.invokePrintLine()
    c.invokePrintLine(true)
}

fun printLine() {
    println("Top-level function")
}

class C {
    fun printLine() {
        println("Member function")
    }

    fun invokePrintLine(omitThis: Boolean = false) {
        if (omitThis) printLine() // top-level
        else this.printLine() // member function
    }
}
