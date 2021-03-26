package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker
import kotlin.properties.Delegates

class Delegation : Worker {

    // The {Delegation pattern} has proven to be a good alternative to implementation {inheritance}
    override fun run() {
        super.run()

        val runDuck = Duck(RunBehavior())
        runDuck.makeBehavior()

        val flyDuck = Duck(FlyBehavior())
        flyDuck.makeBehavior()

        println(max)
        max = 10
        println(max)
        max = 5
        println(max)

        println(min)
        min = "min"
    }

    interface MoveBehavior {
        fun makeBehavior()
    }

    class RunBehavior: MoveBehavior {
        override fun makeBehavior() {
            println("Run!")
        }
    }

    class FlyBehavior: MoveBehavior {
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

    var min: String by Delegates.observable("init") {
        prop, old, new -> println("changed!! $old -> $new")
    }

    var max:Int by Delegates.vetoable(0) {
        prop, old, new -> new > old // accept if true
    }
}
