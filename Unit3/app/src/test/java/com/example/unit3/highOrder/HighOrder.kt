package com.example.unit3.highOrder

import org.junit.Test

class HighOrder {
    class Cookie(val name:String,
                 val softBaked:Boolean ,
                 val hasFilling:Boolean,
                 val price:Double)

    val cookies= listOf(
        Cookie(
            name = "Chocolate Chip",
            softBaked = false,
            hasFilling = false,
            price = 1.69
        ),
        Cookie(
            name = "Banana Walnut",
            softBaked = true,
            hasFilling = false,
            price = 1.49
        ),
        Cookie(
            name = "Vanilla Creme",
            softBaked = false,
            hasFilling = true,
            price = 1.59
        ),
        Cookie(
            name = "Chocolate Peanut Butter",
            softBaked = false,
            hasFilling = true,
            price = 1.49
        ),
        Cookie(
            name = "Snickerdoodle",
            softBaked = true,
            hasFilling = false,
            price = 1.39
        ),
        Cookie(
            name = "Blueberry Tart",
            softBaked = true,
            hasFilling = true,
            price = 1.79
        ),
        Cookie(
            name = "Sugar and Sprinkles",
            softBaked = false,
            hasFilling = false,
            price = 1.39
        )
    )
    @Test
    fun testPrintln(){
        cookies.forEach{
            println("Menu item : ${it.name}  ${it.price}")
        }

    }
    @Test
    fun testMap(){
        val fullMenu=cookies.map {
            "${it.name} -$${it.price}"
        }

        println("Full Menu")
        fullMenu.forEach{
            println(it          )
        }
    }
    @Test
    fun testFilter(){
        val softBakedMenu=cookies.filter {
            it.softBaked
        }
        println("Soft Cookies: ")
        softBakedMenu.forEach{
            println("${it.name}")
        }
    }

    @Test
    fun testGroupBy(){
        val groupedMenu=cookies.groupBy {
            it.softBaked
        }
        val softBakedMenu = groupedMenu[true] ?: listOf()
        val crunchyMenu = groupedMenu[false] ?: listOf()


        println("Soft Cookies: ")
        softBakedMenu.forEach{
            println("${it.name} -$${it.price}")
        }
        println("------------------")
        println("Crunchy Cookies: ")
        crunchyMenu.forEach{
            println("${it.name} -$${it.price}")
        }

    }


    @Test
    fun testFold(){
        val totalPrice= cookies.fold(0.0){
                total,cookie -> total+cookie.price
        }

        println("Total price: ${totalPrice}")
    }
    @Test
    fun testSortBy(){
        val sortMenu=cookies.sortedBy {
            it.name
        }
        println("Sorted By Menu")
        sortMenu.forEach {
            println(it.name)
        }
    }
}