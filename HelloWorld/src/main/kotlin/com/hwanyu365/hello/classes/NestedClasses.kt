package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class NestedClasses: Worker {
    override fun run() {
        super.run()

        ContainerClass.NestedClass().introduce()
        object : ContainerInterface.NestedInterface {}.introduce()
//        InnerClassContainer.InnerClass().introduce() // can not access because it is inner class
        InnerClassContainer().introduceInner()
    }
}

class ContainerClass {
    class NestedClass {
        fun introduce() {
            println("I am ${javaClass.simpleName}")
        }
    }
    interface NestedInterface
}

interface ContainerInterface {
    class NestedClass
    interface NestedInterface {
        fun introduce() {
            println("I am ${javaClass.simpleName}")
        }
    }
}

class InnerClassContainer {

    fun introduceInner() {
        InnerClass().introduce()
    }

    inner class InnerClass {
        fun introduce() {
            println("I am ${javaClass.simpleName}")
        }
    }
}