package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker
import java.lang.IllegalArgumentException

/**
 *
 * Ref.https://medium.com/android-news/kotlin-what-is-a-sealed-classe-1e535c416519
 *
 * Both have the same behavior as an abstract class, preventing directly instantiation and allowing us to declare abstract methods:
 * In addition, enum can have just a single instance, whereas a subclasses of a sealed class can have multiple instances.
 * Important to realize here is what kind of problem you are handling. If you need a constant behavior, so your pick is an enum, otherwise, sealed is your choice.
 */
class SealedClasses : Worker {
    override fun run() {
        super.run()

        val intention: Intention = Intention.LoadMore()
        val output = when (intention) {
            is Intention.Refresh -> "refresh"
            is Intention.LoadMore -> "load more"
        }
        println(output)
    }

    fun eval(e: Expr) {
        when(e) {
            is Expr.Const -> e.number
            is Expr.Sum -> e.e1.number + e.e2.number
            is Expr.NotANumber -> throw IllegalArgumentException("This is not number")
        }
    }

    fun eval2(e: Any) {
        when (e) {
            is Int -> (e + 1)
        }
    }
}

/**
 * A sealed class is abstract by itself, it cannot be instantiated directly and can have abstract members.
 * Sealed classes are not allowed to have non- private constructors (their constructors are private by default).
 */
sealed class Expr {
    data class Const(val number: Double) : Expr()
    data class Sum(val e1: Const, val e2: Const) : Expr()
    object NotANumber : Expr()
}

sealed class Intention {
    class Refresh() : Intention()
    class LoadMore : Intention()
}