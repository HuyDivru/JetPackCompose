package com.example.unit2
import org.junit.Test
class Lamdba {
    @Test
    fun main(){
        val trickFunction=trick
        trick()
        trickFunction()
        treat()
    }
//    fun trick(){
//
//    }
    val trick={
       println("No treats")
    }
    val treat :()->Unit = {
        println("Have a treat")
    }


    @Test
    fun test(){

//        val coins : (Int) -> String ={quantity -> "$quantity quarters"}

        val coins: (Int) ->String ={"$it quarters"}

//        val treatFunction=trickOrTreat(false,coins)
        val treatFunction=trickOrTreat(false){"$it quarters"}
        val trickFunction=trickOrTreat(true,null)
        repeat(5){
            treatFunction()
        }

        trickFunction()
    }

    fun trickOrTreat(isTrick:Boolean,extraTest: ((Int)->String)?) : () ->Unit{
        if(isTrick){
            return trick
        }
        else {
            if(extraTest!=null){
                println(extraTest(5))
            }
            return treat
        }
    }
}