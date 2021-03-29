package com.hwanyu365.hello.functions

import com.hwanyu365.hello.Worker

class Function: Worker {
    override fun run() {
        super.run()

        println("== Default arguments ==")
        funcParamsWithDefault() // You can skip some arguments with default values.
        funcParamsWithDefault("hwanyu365", 37)

        println("\n== Default arguments with Lambda ==")
        funcParamsWithLambda { print("set only lambda > ") }
        funcParamsWithLambda ("hwanyu365") { print("set name & lambda > ") }
        funcParamsWithLambda(age = 37) { print("set age & lambda > ") } // you can name one or more of its arguments
        funcParamsWithLambda("hwanyu365", 37) { print("set name, age & lambda > ") }

        println("\n== Variable number of arguments ==")
        funcVariableArgs("a", "b", "c",)

        println("\n== Infix notation ==")
        val list = arrayListOf("banana", "peach", "apple")
        println("It the list contains apple? ${list funcInfixNotation "apple"}")

        println("\n== Local functions ==")
        localFunc("params")
    }

    private fun funcParamsWithDefault(name:String = "Unknown", age:Int = 0) {
        println("name=$name, age=$age")
    }

    private fun funcParamsWithLambda(name: String = "Unknown", age: Int = 0, run: () -> Unit) {
        run()
        println("name=$name, age=$age")
    }

    private fun funcVariableArgs(vararg args:String) {
        println(args.contentToString())
    }

//    They must be member functions or extension functions.
//    They must have a single parameter.
//    The parameter must not accept variable number of arguments and must have no default value.
    private infix fun List<String>.funcInfixNotation(find:String) = this.contains(find)

    private fun localFunc(param:String) {
        fun concat(postFix: String) {
            println("${param}_$postFix")
        }

        concat("postfix")
    }
}