package com.hwanyu365.hello.controlflow

import com.hwanyu365.hello.Worker

class ControlFlow : Worker {

    override fun run() {
        ConditionsAndLoops().run()
        ReturnsAndJumps().run()
        Exceptions().run()
    }
}