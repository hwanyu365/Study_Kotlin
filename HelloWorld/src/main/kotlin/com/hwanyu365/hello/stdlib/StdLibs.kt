package com.hwanyu365.hello.stdlib

import com.hwanyu365.hello.Worker
import com.hwanyu365.hello.stdlib.collections.Collections

class StdLibs:Worker {
    override fun run() {
        super.run()
        Collections().run()
        ScopeFunctions().run()
    }
}