package com.hwanyu365.hello.controlflow

import com.hwanyu365.hello.Worker

class ConditionsAndLoops : Worker {
    override fun run() {
        super.run()
        exprIf()
        exprWhen()
        exprLoop()
    }

    private fun exprLoop() {
        println("\n== Loop expression ==")

        val items = arrayOf("intel", "asus", "amd", "nvidia")
        var strArray = "";
        for (item in items) {
//            strArray += when (strArray.length) {
//                0 -> {
//                    item
//                }
//                else -> {
//                    ", $item"
//                }
//            }
            strArray += if (strArray.isNotEmpty()) ", $item" else item
        }
        println(strArray)

        strArray = ""
        for ((i, v) in items.withIndex()) {
            strArray += if (strArray.isNotEmpty()) ", $i: $v" else "$i: $v"
        }
        println(strArray)

        strArray = ""
        for (odd in 0..10 step 2) {
            strArray += if (odd != 0) (if (strArray.isNotEmpty()) ", $odd" else odd) else continue
        }
        println(strArray)
    }

    private fun exprWhen() {
        println("\n== When expression ==")
//        var x: Int? = null
        var x: Int = 50
        val low = 50
        val medium = 80

        if (x !== null) {
            val result = when (x) {
//                in 0..low -> {
                in low downTo 0 -> {
                    "Low"
                }
//                in (low + 1)..medium -> {
                in medium downTo (low + 1) -> {
                    "Medium"
                }
                else -> {
                    "High"
                }
            }
            println("$x is $result score.")
        } else {
            println("x is null")
        }

        // useful syntax
        val usefulSyntax = """
            |Useful Syntax
            fun Request.getBody() = when (val response = executeRequest()) {
                is Success -> response.body
                is HttpError -> throw HttpException(response.status)
            }
        """.trimMargin()
        println(usefulSyntax)
    }

    private fun exprIf() {
        println("== If expression ==")
        max(5, 10)
    }

    private fun max(a: Int, b: Int) {
        val min = if (a < b) a else b
        val max = if (a > b) {
            a
        } else {
            b + 15
            b + 2 // the last expression is the value of a block
        }
        println("max value is $max")
    }
}