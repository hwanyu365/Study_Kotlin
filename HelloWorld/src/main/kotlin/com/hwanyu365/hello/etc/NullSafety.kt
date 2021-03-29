package com.hwanyu365.hello.etc

import com.hwanyu365.hello.Worker

class NullSafety : Worker {
    override fun run() {
        super.run()
        nullableAndNonNullableType()
    }

    /**
     *
     * Kotlin's type system is aimed to eliminate NullPointerException 's from our code. The only possible causes of NPE's may be:
     * - An explicit call to throw NullPointerException().
     * - Usage of the {!!} operator that is described below.
     * - Some data inconsistency with regard to initialization, such as when:
     *   - An uninitialized this available in a constructor is passed and used somewhere ("leaking this ").
     *   - A superclass constructor calls an open member whose implementation in the derived class uses uninitialized state.
     */

    private fun nullableAndNonNullableType() {
        println("== Nullable And Non-nullable")
        var a: String = "abc" // NonNullable
        var b: String? = null // Nullable
        println("$a, $b")

//        b.length // BUILD-TIME ERROR, cannot this because b is nullable
        println("b.length = ${b?.length}") // null, Safe calls
        println("b.length = ${b?.length ?: -1}") // -1, elvis operator
//        println("b.length = ${b!!.length}") // throw NPE, for NPE lovers

//        To perform a certain operation only for non-null values, you can use the safe call operator together with {let}:
        val listWithNulls: List<String?> = listOf("Kotlin", null)
        for (item in listWithNulls) {
            item?.let { println(it) } // prints Kotlin and ignores null
        }

        for (item in listWithNulls.filterNotNull()) {
            println(item)
        }
    }
}