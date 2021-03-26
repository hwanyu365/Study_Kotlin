package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class ClassesAndObjects : Worker {

    /**
     * class
     * data
     * object
     * companion object
     */
    override fun run() {
        Classes().run()
        Inheritance().run()
        Properties().run()
        Interfaces().run()
        Extensions().run()
        DataClasses().run()
        SealedClasses().run()
        Generics().run()
        NestedClasses().run()
        EnumClasses().run()
        ObjectExpressionsAndDeclarations().run()
        Delegation().run()
    }
}
