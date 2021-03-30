package com.hwanyu365.hello.util

fun <T> List<T>.printAll(prefix: String? = null) {
    println("${if (prefix !== null) "$prefix=" else ""}$this")
}

fun <K, V> Map<K, V>.printAll(prefix: String? = null) {
    println("${if (prefix !== null) "$prefix=" else ""}$this")
}