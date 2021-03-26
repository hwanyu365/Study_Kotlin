package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Interfaces : Worker {
    override fun run() {
        super.run()
        val test: MyInterface = Child()
        test.foo()

        val myThread = MyThread()
        myThread.start { println("Do not use SAM") }
    }

    interface MyInterface {
        val prop: Int // abstract

        val propertyWithImplementation: String
            get() = "foo"

        fun foo() {
            println(prop)
        }
    }

    class Child : MyInterface {
        override val prop: Int = 29
        override val propertyWithImplementation: String = "Child"
    }

    interface Named {
        val nick: String
        val name: String
    }

    interface Person : Named {
        val firstName: String
        val lastName: String
        override val name: String get() = "$firstName $lastName"
    }

    data class Employee(
        // implementing 'name' is not required
        override val firstName: String,
        override val lastName: String,
        override val nick: String,
    ) : Person

    // functional interface = SAM (Single Abstract Method) interface
    fun interface MyRunner {
        fun run()
    }

    class MyThread {
        fun start(runner: MyRunner) {
            // set new thread environment
            runner.run()
        }
    }
}
