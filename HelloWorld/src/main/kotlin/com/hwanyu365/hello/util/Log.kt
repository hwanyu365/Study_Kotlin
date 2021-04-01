package com.hwanyu365.hello.util

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun <T> List<T>.log(prefix: String? = null) {
    println("${if (prefix !== null) "$prefix=" else ""}$this")
}

fun <K, V> Map<K, V>.log(prefix: String? = null) {
    println("${if (prefix !== null) "$prefix=" else ""}$this")
}
