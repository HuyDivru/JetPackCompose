package com.example.unit2

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testWhen(){
        val lightBlack="Black"
        when(lightBlack){
            "Red" -> println("Stop")
            "Green" -> println("Let go")
            "Yellow" -> println("Slow")
            else -> println("invalid traffic-light color")
        }
    }
}
