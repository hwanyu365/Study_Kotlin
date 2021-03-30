package com.hwanyu365.hello.etc

import com.hwanyu365.hello.Worker

class Etc:Worker {

    override fun run() {
        super.run()
        NullSafety().run()
        ThisExpressions().run()
        OperatorOverloading().run()
        DestructuringDeclarations().run()
    }
}