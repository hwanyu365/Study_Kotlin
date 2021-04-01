package com.hwanyu365.hello.officiallib

import com.hwanyu365.hello.Worker
import com.hwanyu365.hello.officiallib.coroutines.Coroutines

class OfficialLib : Worker {
    override fun run() {
        super.run()
        Coroutines().run()
    }
}