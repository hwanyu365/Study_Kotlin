package com.hwanyu365.hello.controlflow

import com.hwanyu365.hello.Worker

class ReturnsAndJumps : Worker {
    override fun run () {
        super.run()
        returnAtLabels()
    }

    private fun returnAtLabels() {
//        listOf(1, 2, 3, 4, 5).forEach {
//            if (it == 3) return
//            print(it)
//        }
//        print(" cannot reach here")

        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop (≒ continue in loop)
            print(it)
        }
        println(" done with explicit label")

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // local return to the caller of the lambda - the forEach loop
            print(it)
        }
        println(" done with implicit label")

        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // non-local return from the lambda passed to run (≒ break in loop)
                print(it)
            }
        }
        println(" done with nested loop")

        val x = run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop "WTF!! Awesome!" // return value at label @loop
                print(it)
            }
        }
        println(" done with nested loop. returned $x")
    }
}