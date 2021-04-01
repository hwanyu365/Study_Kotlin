package com.hwanyu365.hello.stdlib.collections

import com.hwanyu365.hello.Worker

class RangesAndProgressions : Worker {
    override fun run() {
        super.run()

        for (i in 1..5) { // i in [1, 5]
            print(i)
        }
        println()

        for (i in 'a'..'z') {
            print(i)
        }
        println()

        for (i in 5 downTo 1) { // 5..1 as literals
            print(i)
        }
        println()

        for (i in 1..10 step 2) {
            print(i)
        }
        println()

        for (i in 9 downTo 1 step 2) {
            print(i)
        }
        println()

        for(i in 1 until 10) {  // i in [1, 10), 10 is excluded
            print(i)
        }
        println()

        for (i in 1 until 10 step 2) {
            print(i)
        }
        println()
    }
}