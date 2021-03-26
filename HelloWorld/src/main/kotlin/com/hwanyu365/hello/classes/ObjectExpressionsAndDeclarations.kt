package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class ObjectExpressionsAndDeclarations : Worker {

    /**
     * Semantic difference between object expressions and declarations
     * There is one important semantic difference between object expressions and object declarations:
     * - Object expressions are executed (and initialized) immediately, where they are used.
     * - Object declarations are initialized lazily, when accessed for the first time.
     * - A companion object is initialized when the corresponding class is loaded (resolved), matching the semantics of a Java static initializer.
     */
    override fun run() {
        super.run()
        exprObject()
        declareObject()
        companionObjects()
    }

    private fun companionObjects() {
        println("\n== Companion Objects ==")
        CompanionDemo.run()
        CompanionDemo.printName()
    }

    private fun declareObject() {
        println("\n== Object declarations ==")
        CarFactory.ready()
        CarFactory.run(1600)
    }

    // Note that anonymous objects can be used as types only in local and private declarations.
    // If you use an anonymous object as a return type of a public function or the type of a public property,
    // the actual type of that function or property will be the declared supertype of the anonymous object, or Any
    private fun exprObject() {
        println("== Object expressions ==")
        val coordX = 19.2
        val coordY = 20.9
        val coords = object {
            // The code in object expressions can access variables from the enclosing scope.
            val x = coordX
            val y = coordY
        }
        println("coordinate are ${coords.x}, ${coords.y}")
    }
}

/**
 * Object declaration's initialization is thread-safe and done at first access.
 */
interface Factory {
    fun ready()
}

object CarFactory : Factory {
    override fun ready() {
        println("Ready to run")
    }

    fun run(cc: Int): Any {
        println("create a car (${cc}cc)")
        return "Car(${cc}cc)"
    }
}

class CompanionDemo {
    companion object : Worker {
        fun printName() {
            println("This is companion object")
        }
    }
}