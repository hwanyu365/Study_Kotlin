package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

typealias NameSet<T> = MutableMap<String, T>

class TypeAliases : Worker {
    override fun run() {
        super.run()
        val nameSet: NameSet<Any?> = mutableMapOf(
            "name" to "hwanyu365",
            "age" to 37
        )
        println("name=${nameSet["hwanyu365"]}")
    }
}