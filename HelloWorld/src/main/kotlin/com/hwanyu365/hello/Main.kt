package com.hwanyu365.hello

import com.hwanyu365.hello.classes.ClassesAndObjects
import com.hwanyu365.hello.controlflow.ControlFlow
import com.hwanyu365.hello.types.Types

//import com.hwanyu365.hello.types.TypeCheckAndCast as tca

fun main() {
//    launch<HelloApp>()

    Types().run()
    ControlFlow().run()
    ClassesAndObjects().run()
}