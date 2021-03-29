package com.hwanyu365.hello.functions

import com.hwanyu365.hello.Worker

class Functions: Worker {
    override fun run() {
        super.run()
        Function().run()
        Lambdas().run()
    }
}