package com.example.unit3

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
    //Enum class
    enum class Direction {
        EASY,MEDIUM,HARD
    }
    //data class
    data class User<T>(val question:String , val answer:T,val diffculty:Direction)
    @Test
    fun testData(){
        val question1= User("What is 2+2",4,Direction.EASY)
        val question2=User("What is 2*2",'4',Direction.MEDIUM)
        val question3=User("What is 2^2","4",Direction.HARD)

        println(question2.toString())

        question2.let {
            println(it.question)
            println(it.answer)
            println(it.diffculty)
        }
    }
    @Test
    fun testNum(){
        val num= arrayOf(1,2,3,4,5,6,7,8,9,10);
        println(num[2])
    }
    @Test
    fun testSet(){
        val test= mutableSetOf<Int>(3,3,2,2,1)
        println(test)
    }
    @Test
    fun testMap(){
        val test= mutableMapOf(
            "Mercury" to 0,
            "Venus" to 0,
            "Earth" to 1,
            "Mars" to 2,
            "Jupiter" to 79,
            "Saturn" to 82,
            "Uranus" to 27,
            "Neptune" to 14
        )
        println(test["Venus"])
    }
}