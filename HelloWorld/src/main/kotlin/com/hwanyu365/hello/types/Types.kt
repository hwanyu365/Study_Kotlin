package com.hwanyu365.hello.types

import com.hwanyu365.hello.Worker

class Types: Worker {
    override fun run() {
        BasicTypes().run()
        TypeCheckAndCast().run()
    }
}