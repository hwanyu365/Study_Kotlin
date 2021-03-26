package com.hwanyu365.hello.controlflow

import com.hwanyu365.hello.Worker

class Exceptions:Worker {

    override fun run() {
        super.run()

        try {
            val x: Any = "one"
            val y: Int = x as Int
            println("y is $y")
        } catch (e: Exception) {
            println("Occurred an exception(${e.javaClass}: ${e.message})")
            e.printStackTrace()
        }

//        throwExceptionAlways("invalid argument")
    }

//    private fun checkedException() throws { not support 'throws' (checked exception)
//    Examination of small programs leads to the conclusion that requiring exception specifications could both enhance developer productivity and enhance code quality,
//    but experience with large software projects suggests a different result – decreased productivity and little or no increase in code quality.
//    }

    private fun throwExceptionAlways(reason: String): Nothing { // if a function has the return type of Nothing, it means that it never returns (always throws an exception).
        throw IllegalArgumentException(reason)
    }
}