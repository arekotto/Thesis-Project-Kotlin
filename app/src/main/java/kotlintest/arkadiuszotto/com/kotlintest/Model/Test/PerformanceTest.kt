package kotlintest.arkadiuszotto.com.kotlintest.Model.Test

import kotlintest.arkadiuszotto.com.kotlintest.Model.User
import java.util.*

/**
 * @author arekotto
 */
interface PerformanceTest {
    fun run(iterations: Long): Double
}

enum class PerformanceTestType {
    LIST_ADD, LIST_SORT, STRINGS, CLASSES
}

class ListAddTest: PerformanceTest {
    override fun run(iterations: Long): Double {
        val list = mutableListOf<Long>()
        var i = 0L
        val startTime = System.nanoTime()
        while (i < iterations) {
            i++
            list.add(i)
            if (list.size > 10000) {
                list.clear()
            }
        }
        val duration = System.nanoTime() - startTime
        return duration / 1000000000.0
    }
}

class ListSortTest: PerformanceTest {
    override fun run(iterations: Long): Double {

        val list = mutableListOf<Int>()
        var i = 0L
        val random = Random()
        while (i < iterations) {
            i++
            list.add(random.nextInt(100))
        }
        val startTime = System.nanoTime()
        list.sort()
        val duration = System.nanoTime() - startTime
        return duration / 1000000000.0
    }
}

class StringsTest: PerformanceTest {
    override fun run(iterations: Long): Double {
        var i = 0L
        var string = ""
        val startTime = System.nanoTime()
        while (i < iterations) {
            i++
            string = "Example Number: $i"
        }
        val duration = System.nanoTime() - startTime
        string + ""
        return duration / 1000000000.0
    }
}

class ClassesTest: PerformanceTest {
    override fun run(iterations: Long): Double {
        var i = 0L
        val startTime = System.nanoTime()
        while (i < iterations) {
            i++
            val user = User("John Smith", i.toInt())
        }
        val duration = System.nanoTime() - startTime
        return duration / 1000000000.0
    }
}