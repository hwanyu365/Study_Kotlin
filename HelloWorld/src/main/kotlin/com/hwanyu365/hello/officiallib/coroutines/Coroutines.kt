package com.hwanyu365.hello.officiallib.coroutines

import com.hwanyu365.hello.Worker

class Coroutines : Worker {
    override fun run() {
        super.run()
        CoroutinesBasics().run()
    }
}