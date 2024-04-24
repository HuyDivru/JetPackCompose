package com.nhathuy.unit6.learncoroutine

import kotlinx.coroutines.*
import org.junit.Test

class LearnCoroutine {
    @Test
    fun main(){
        runBlocking {
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
        }
        println("Hello Huy Bro")
    }
    @Test
    fun testDeferred(){
        runBlocking {
            println("Weather forecast")
            val forecast:Deferred<String> = async {
                getForecast()
            }
            val tempeature : Deferred<String> = async {
                getTemperature()
            }
            println("${forecast.await()} ${tempeature.await()}")
            println("Have a good day!")
        }
    }

    //test exception
    @Test
    fun textException(){
        runBlocking {
            println("Weather forecast")
            println(getWeatherReport())
            println("Have a good day")
        }
    }
    suspend fun getWeatherReport()= coroutineScope {
        val forecast=async {
            getForecast()
        }
        val temperature = async {
            try {
                getTemperatures()
            }
            catch (e : AssertionError){
                println("Caught exception $e")
                "{No temperature found}"
            }
        }
        "${forecast.await()} ${temperature.await()}"
    }

    //Test dispatcher
    @Test
    fun testDispater(){
        runBlocking {
            println("${Thread.currentThread().name} - runBlocking function")
            launch {
                println("${Thread.currentThread().name} - launch function")
                withContext(Dispatchers.Default){
                    println("${Thread.currentThread().name} - withContext function")
                    delay(1000)
                    println("10 results found.")
                }
                println("${Thread.currentThread().name} - end of launch  function")
            }
            println("Loading...")
        }
    }
    suspend fun getTemperatures():String{
        delay(450)
        throw AssertionError("Temperature is invalid")
        return "30°C"
    }

    suspend fun  getForecast():String{
        delay(1000)
        return "Sunny"
    }
    suspend fun  getTemperature():String{
        delay(1000)
        return "30°C"
    }
    suspend fun printForecast(){
        delay(1000)
        println("Sunny")
    }
    suspend fun printTemperature(){
        delay(1000)
        println("30\u00b0C")
    }
}