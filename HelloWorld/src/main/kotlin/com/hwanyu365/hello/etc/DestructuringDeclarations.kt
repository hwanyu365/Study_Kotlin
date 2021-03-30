package com.hwanyu365.hello.etc

import com.hwanyu365.hello.Worker

class DestructuringDeclarations : Worker {
    override fun run() {
        super.run()

        println("== Two values ==")
        val (name, age) = Pair("hwanyu365", 37)
        println("name=$name, age=$age")

        println("\n= Destructuring declarations and maps ==")
        val map = mapOf("hwanyu365" to 37, "jake" to 20)

        print("[")
        var index = 0
        for ((k, v) in map) {
            if (index != 0) {
                print(", ")
            }
            print("$k:$v")
            index++
        }
        println("]")

        // If you don't need a variable in the destructuring declaration, you can place an underscore instead of its name:
        // map.mapValues { (_, value): Map.Entry<Int, String> -> "$value!" }
    }
}