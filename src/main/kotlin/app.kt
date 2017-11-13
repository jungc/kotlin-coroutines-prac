
import kotlinx.coroutines.experimental.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    test3()
}

fun test2() {
    val c = AtomicInteger()

    for (i in 1..1_000_000)
        thread(start = true) {
            c.addAndGet(i)
        }

    println(c.get())
}

fun test3() {
    val c = AtomicInteger()

    for (i in 1..1_000_000)
        launch {
            c.addAndGet(i)
        }

    println(c.get())
}

fun test1() {
    println("Start")

    // Start a coroutine
    launch {
        delay(1000) // none-thread blocking. Frees up thread until it wakes up again
        println("Hello")
    }

    runBlocking {
        delay(2000)
        println("Hello2")
    }

    Thread.sleep(2000) // thread blocking
    println("Stop")
}