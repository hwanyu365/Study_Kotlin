package com.hwanyu365.hello

interface Worker {
    fun run() {
        println("\n\n====== ${javaClass.simpleName} ======")
    }
}