package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Delegation : Worker {

    // The {Delegation pattern} has proven to be a good alternative to implementation {inheritance}
    override fun run() {
        super.run()

        val runDuck = Duck(RunBehavior())
        runDuck.makeBehavior()

        val flyDuck = Duck(FlyBehavior())
        flyDuck.makeBehavior()

        delegatedProperties()
        lazyProperties()
        observableAndVetoableProperties()
        delegatingToAnotherProperty()
        storingPropertiesInMap()
    }

    private fun delegatedProperties() {
        println("\n== Delegated Properties ==")

        var e = Example()
        println(e.p)
        e.p = "NEW"
    }

    val lazyValue: String by lazy {
        println("lazy initialized")
        "Hello"
    }

    private fun lazyProperties() {
        println("\n== Lazy Properties ==")
        println(lazyValue)
        println(lazyValue)
    }

    private fun observableAndVetoableProperties() {
        println("\n== Observable and Vetoable Properties ==")

        // observable
        println(min)
        min = "min"

        // vetoable
        println(max)
        max = 10
        println(max)
        max = 5
        println(max)
    }

    private fun delegatingToAnotherProperty() {
        println("\n== Delegating to another property ==")

        val p = Person()
        p.name = "Jay"
        println(p.uniqueName)

        p.uniqueName = "Jake"
        println(p.name)
    }

    private fun storingPropertiesInMap() {
        println("\n== Storing properties in a map ==")

        var user = User(
            mapOf(
                "name" to "hwanyu365",
                "age" to 37
            )
        )
        println("name=${user.name}, age=${user.age}")

        var mutableUser = MutableUSer(
            mutableMapOf(
                "name" to "hwanyu365",
                "age" to 37
            )
        )
        println("name=${mutableUser.name}, age=${mutableUser.age}")
    }

    class User(map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    class MutableUSer(map: MutableMap<String, Any?>) {
        var name: String by map
        var age: Int by map
    }

    class Person {
        var name: String = ""

        @Deprecated("Use 'name' instead", ReplaceWith("name"))
        var uniqueName: String by this::name
    }

    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name}' in $thisRef")
        }
    }

    class Example {
        var p: String by Delegate()
    }

    interface MoveBehavior {
        fun makeBehavior()
    }

    class RunBehavior : MoveBehavior {
        override fun makeBehavior() {
            println("Run!")
        }
    }

    class FlyBehavior : MoveBehavior {
        override fun makeBehavior() {
            println("Fly~~")
        }
    }

    class Duck(b: MoveBehavior) : MoveBehavior by b

// Java
//    interface MoveBehavior {
//        public void makeBehavior();
//    }
//
//    public class RunBehavior implements MoveBehavior {
//        @Override
//        public void makeBehavior() {
//            System.out.println("make a run");
//        }
//    }
//
//    public class FlyBehavior implements MoveBehavior {
//        @Override
//        public void makeBehavior() {
//            System.out.println("make a fly");
//        }
//    }
//
//    public class Duck {
//        private MoveBehavior moveBehavior = new RunBehavior();
//
//        public void makeBehavior() {
//            this.moveBehavior.makeBehavior();
//        }
//
//        public void setMoveBehavior(MoveBehavior moveBehavior) {
//            this.moveBehavior = moveBehavior;
//        }
//    }
//
//    public void main() {
//        Duck duck = new Duck();
//        duck.makeBehavior();
//        MoveBehavior newBehavior = new FlyBehavior();
//        duck.setMoveBehavior(newBehavior);
//        duck.makeBehavior();
//    }

    var min: String by Delegates.observable("init") { prop, old, new ->
        println("changed!! $old -> $new")
    }

    var max: Int by Delegates.vetoable(0) { prop, old, new ->
        new > old // accept if true
    }
}
