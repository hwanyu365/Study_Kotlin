package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker
import java.util.concurrent.Executor

class Generics : Worker {
    // T : general usages, it means just T type object. so you cannot this: 'animal:Custom<Animal> = cat:Custom<Cat>'
    // out T : extends of T > Custom<out T> / animal:Custom<Animal> = cat:Custom<Cat>
    // in T : super of T    > Custom<in T> / cat:Custom<Cat> = animal:Custom<Animal>
    override fun run() {
        super.run()

        val myList = GenericList<String>()
        myList.add("hwanyu365")
        myList.add("jake")
        println("size= ${myList.size}")
        myList.prints()
    }

    fun typeProjection() {
        // user-site variance
        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { "" }
        copy(ints, any)
    }

    //           ↓ type is Array<Int> but Array<Any> was expected
//    fun copy(from: Array<Any>, to: Array<Any>) {
//        assert(from.size == to.size)
//        for (i in from.indices)
//            to[i] = from[i]
//    }

    fun copy(from: Array<out Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    //        ↓ Array<String> type is expected, dest param can be set as 'Array<CharSequence>' // String extends CharSequence)
    fun fill(dest: Array<in String>, value: String) {
        for (i in dest.indices) {
            dest[i] = value
        }
    }

    // function can have type parameters
    fun <T> singleToneList(item: T) {
        // blah blah
        Int
    }

    fun <T> T.singleToneListExt() {
        // blah blah
    }

    fun <T, X> comp(comp: T, comp2: X)
            where T : Number,
                  T : Executor,
                  X : CharSequence,
                  X : Number {
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

    // it means <? extends T?, unfortunately jvm is prohibits Sources<T> = Sources<X> (X extends T)
//    interface Source<T> {
    interface Source<out T> { // {out} modifier is called {variance annotation}, it provides declaration-site variance.
        fun next(): T
    }

    fun demo(strT: Source<String>) {
        val anyT: Source<Any> = strT // this is not work if you do not use declaration-site variance
    }
//   ^ type is Array<Int> but Array<Any> was expected
}
