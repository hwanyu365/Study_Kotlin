package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Generics : Worker {
    override fun run() {
        super.run()

        val myList = GenericList<String>()
        myList.add("hwanyu365")
        myList.add("jake")
        println("size= ${myList.size}")
        myList.prints()
    }
}

class GenericList<T> {
    val size: Int
        get() = _list.size
    private val _list = HashMap<Int, T>()

    fun add(item: T) {
        _list[item.hashCode()] = item
    }

    fun contains(item: T) = _list.containsValue(item)

    fun prints() {
        var strList = ""
        _list.forEach { (t, u) ->
            run {
                strList += if (strList.isNotEmpty()) ", " else ""
                strList += "key=$t, value=$u"
            }
        }
        println(strList)
    }
}