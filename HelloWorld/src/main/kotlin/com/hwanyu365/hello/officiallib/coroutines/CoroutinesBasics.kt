package com.hwanyu365.hello.officiallib.coroutines

import com.hwanyu365.hello.Worker
import com.hwanyu365.hello.util.log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

class CoroutinesBasics : Worker {
    override fun run() {
        super.run()
//        runCoroutineFunc()
//        waitingJob()
//        innerCoroutines()
//        scopeBuilder()
//        lightWeightCoroutines()
//        globalCoroutines()
//        asyncAndAwait()
//        cancellation()
//        notCancellable()
//        nonCancellableBlock()
//        withTimeout()
//        asyncWithTimeout()
//        composingSuspendingFuncs()
//        asyncComposingSuspendingFuncs()
//        asyncStyleComposingSuspendingFuncs()
//        asyncStyleCoroutineScopeFun()
//        parentalResponse()
//        namingCoroutines()
//        threadLocal()
        sequences()
        suspendingFunctions()
        flows()
    }

    private fun runCoroutineFunc() = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        print("Hello, ")
        delay(2000L)
    }

    private fun waitingJob() = runBlocking {
        val job = GlobalScope.launch {
            delay(1000L)
            println("World!") // Without job.join(), 'World!' is not printed if program is end in 1000ms
        }
        print("Hello, ")
//        job.join()
        println("!EOF!")
    }

    private fun innerCoroutines() = runBlocking {
        launch {
            delay(1000L)
            println("World!") // No need to call Job.join(), outer coroutine is blocking until all inner coroutines is completed
        }
        print("Hello, ")
    }

    private fun scopeBuilder() = runBlocking { // this: CoroutineScope
        launch {
            delayedPrint(200L, "Task from runBlocking")
        }

        coroutineScope { // Creates a coroutine scope
            launch {
                delayedPrint(500L, "Task from nested launch")
            }
            delayedPrint(100L, "Task from coroutine scope") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over") // This line is not printed until the nested launch completes
    }

    private suspend fun delayedPrint(delay: Long, msg: String? = null) {
        delay(delay)
        msg?.run(::println)
    }

    private fun lightWeightCoroutines() = runBlocking {
        val c = AtomicLong()
        val startTimeInMillis = System.currentTimeMillis()
        repeat(1_000_000) { // create 1K coroutines
            launch {
                c.incrementAndGet()
            }.run { join() }
        }
        println("${System.currentTimeMillis() - startTimeInMillis}ms ${c.get()}")

//        val cc = AtomicLong()
//        repeat(1_000_000) { // create 1K coroutines
//            thread {
//                cc.incrementAndGet()
//            }
//        }
//        println(cc.get())
    }

    private fun globalCoroutines() = runBlocking {
        println()
        GlobalScope.launch {
            repeat(1000) { i ->
                delayedPrint(500L, "I am sleeping $i...")
            }
        }
        delay(1500L)
//        Active coroutines that were launched in GlobalScope do not keep the process alive. They are like daemon threads.
    }

    private fun asyncAndAwait() = runBlocking {
        val deferreds: List<Deferred<Int>> = (1..100).map { n -> async { n } } // List<Deferred<Int>>
        val sum = deferreds.sumOf { it.await() * 2 }
        println("sum is $sum")
    }

    private fun cancellation() = runBlocking {
        println("\n== Cancelling coroutine execution ==")
        val job = launch {
            repeat(1000) { i ->
                println("I am sleeping... $i")
                delay(500L)
            }
        }
        delay(1000L)
        job.cancelAndJoin()
        println("I am quit!!")
    }

    private fun notCancellable() = runBlocking {
        println("\n== Cancelling coroutine execution ==")

        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            try {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) { // computation loop, just wastes CPU
                    // print a message twice a second
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            } finally {
                delay(500L)
                println("job: done $isActive")
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    private fun nonCancellableBlock() = runBlocking {
        val job = launch {
            try {
                repeat(500) { i ->
                    println("job: I am sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(100L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    private fun withTimeout() = runBlocking {
        try {
            withTimeout(1000L) {
                repeat(500) { i ->
                    println("job: I am sleeping $i ...")
                    delay(500L)
                }
            }

        } catch (e: TimeoutCancellationException) {
            System.err.println(e.printStackTrace())
        }
    }

    private fun asyncWithTimeout() {
        val c = AtomicInteger()
        runBlocking {
            repeat(100_000) { // Launch 100K coroutines
                launch {
                    withTimeout(100) {
                        delay(50)
                        c.incrementAndGet()
                    }
                    c.decrementAndGet()
                }
            }
        }
        println("main: EOF $c")
    }

    private fun composingSuspendingFuncs() = runBlocking {
        val start = System.currentTimeMillis()
        val a = doSomethingUsefulOne()
        val b = doSomethingUsefulTwo()
        println("a + b = ${a + b}, ${System.currentTimeMillis() - start}")
    }

    private fun asyncComposingSuspendingFuncs() = runBlocking {
        val start = System.currentTimeMillis()
        val a = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val b = async { doSomethingUsefulTwo() }
        a.start()
        println("a + b = ${a.await() + b.await()}, ${System.currentTimeMillis() - start}")
    }

    private fun asyncStyleComposingSuspendingFuncs() {
        val start = System.currentTimeMillis()
        val a = doSomethingUsefulOneAsync()
        val b = doSomethingUsefulTwoAsync()
        runBlocking {
            println("a + b = ${a.await() + b.await()}, ${System.currentTimeMillis() - start}")
        }
    }

    private fun asyncStyleCoroutineScopeFun() = runBlocking {
        var a: Int
        val time = measureTimeMillis {
            a = concurrentSum()
        }
        println("concurrentSum = $a, ${time}ms, ${coroutineContext[Job]}")
    }

    private fun parentalResponse() = runBlocking {
        val request = launch {
            repeat(3) { i -> // launch a few children jobs
                launch {
                    delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // wait for completion of the request, including all its children
        println("Now processing of the request is complete")
    }

    private fun namingCoroutines() = runBlocking {
        log("Started main coroutine")

        val v1 = async(CoroutineName("Coroutine-v1")) {
            delay(500L)
            log("Computing v1")
            6
        }
        val v2 = async(CoroutineName("Coroutine-v2")) {
            delay(500L)
            log("Computing v2")
            7
        }
        log("The answer is ${v1.await() * v2.await()}")
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        coroutineContext[Job]
        one.await() + two.await()
    }

    private fun doSomethingUsefulOneAsync() = GlobalScope.async {
        //                doSomethingUsefulOne()
        suspend fun some(): Int {
            delay(1000L)
            return 15
        }

        some()
    }

    private fun doSomethingUsefulTwoAsync() = GlobalScope.async {
        //        doSomethingUsefulTwo()
        suspend fun some(): Int {
            delay(1000L)
            return 5
        }
        some()
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 15
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 5
    }

    private val threadLocal = ThreadLocal<String>()

    private fun threadLocal() = runBlocking {
        threadLocal.set("thread-local")

        launch(Dispatchers.Default + threadLocal.asContextElement(value = "as context element")) {
            println("${Thread.currentThread().name} : ${threadLocal.get()}")
            yield()
            println("${Thread.currentThread().name} : ${threadLocal.get()}")
        }.run { join() }
        println("${Thread.currentThread().name} : ${threadLocal.get()}")
    }

    private fun simpleSequence(): Sequence<Int> = sequence { // sequence builder - SequenceScope
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    private fun sequences() = runBlocking {
        println("\n== Sequences ==")
        val start = System.currentTimeMillis()
        launch {
            for (k in 11..13) {
                println("I'm not blocked $k, ${System.currentTimeMillis() - start}ms")
                delay(100)
            }
        }
        simpleSequence().forEach { value -> println("$value, ${System.currentTimeMillis() - start}ms") }
    }

    private suspend fun simpleList(): List<Int> {
        delay(100) // pretend we are doing something asynchronous here
        return listOf(1, 2, 3)
    }

    private fun suspendingFunctions() = runBlocking {
        println("\n== Suspending functions ==")
        val start = System.currentTimeMillis()
        launch {
            for (k in 11..13) {
                println("I'm not blocked $k, ${System.currentTimeMillis() - start}ms")
                delay(100)
            }
        }
        simpleList().forEach { value -> println("$value, ${System.currentTimeMillis() - start}ms") }
    }

    private fun simpleFlow(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(100) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }
    private fun flows() = runBlocking {
        // Launch a concurrent coroutine to check if the main thread is blocked
        println("\n== Flows ==")
        val start = System.currentTimeMillis()
        launch {
            for (k in 11..13) {
                println("I'm not blocked $k, ${System.currentTimeMillis() - start}ms")
                delay(100)
            }
        }
        // collect the flow
        simpleFlow().collect { value -> println("$value, ${System.currentTimeMillis() - start}ms") }
    }
}