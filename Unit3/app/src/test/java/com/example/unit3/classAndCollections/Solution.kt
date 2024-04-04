package com.example.unit3.classAndCollections

import org.junit.Test

class Solution {
    //Task 1
    class Event(val title:String, val description:String? = null , val daypart:String, var duration:Int,)
    //Task 2
    enum class Daypart{
        MORNING,AFTERNOON,EVENING,
    }
//     class Event(val title:String, val description:String? = null , val daypart:Daypart, var duration:Int,)

    fun task2(){
        class Event(val title:String, val description:String? = null, val daypart: Daypart, var duration:Int,)
    }

    //Task 3
    @org.junit.Test
    fun task3(){
        class Event(val title:String, val description:String? = null, val daypart:Daypart, var durationInMinutes:Int,){
            override fun toString(): String {
                return "Event $title $description $daypart $durationInMinutes"
            }
        }
        val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
        val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
        val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
        val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
        val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
        val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

        val event= mutableListOf(event1,event2,event3,event4,event5,event6)

        event.forEach {
            println(it.toString())
        }
    }

    //Task 4
    @Test
    fun task4(){
        class Event(val title:String, val description:String? = null, val daypart:Daypart, var durationInMinutes:Int,){
            override fun toString(): String {
                return "Event $title $description $daypart $durationInMinutes"
            }
        }
        val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
        val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
        val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
        val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
        val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
        val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

        val event= mutableListOf(event1,event2,event3,event4,event5,event6);

        val durationThan=event.filter {
            it.durationInMinutes<60
        }
        println("You have ${durationThan.size} events less than 60 minutes")
    }
    //Task 5
    @Test
    fun task5(){
        class Event(val title:String, val description:String? = null, val daypart:Daypart, var durationInMinutes:Int,){
            override fun toString(): String {
                return "Event $title $description $daypart $durationInMinutes"
            }
        }
        val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
        val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
        val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
        val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
        val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
        val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

        val event= mutableListOf(event1,event2,event3,event4,event5,event6);

       val eventGroupBy=event.groupBy {
           it.daypart
        }
        eventGroupBy.forEach {
            println("${it.key} has ${it.value.size} events")
       }
    }

    //Task 6
    @Test
    fun task6(){
        class Event(val title:String, val description:String? = null, val daypart:Daypart, var durationInMinutes:Int,){
            override fun toString(): String {
                return "Event $title $description $daypart $durationInMinutes"
            }
        }
        val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
        val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
        val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
        val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
        val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
        val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

        val event= mutableListOf(event1,event2,event3,event4,event5,event6);

        println("Last event of the day: ${event[event.size-1].title}")
    }

    //Task 7
    @Test
    fun task7(){
        class Event(val title:String, val description:String? = null, val daypart:Daypart, var durationInMinutes:Int,){
            override fun toString(): String {
                return "Event $title $description $daypart $durationInMinutes"
            }
        }
        val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
        val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
        val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
        val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
        val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
        val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

        val event= mutableListOf(event1,event2,event3,event4,event5,event6);

        val durationOfEvent= if(event[0].durationInMinutes<60) {
            "short"
        }else{
            "long"
        }
        println("First event of the day is a $durationOfEvent event")

        println("duration of first event of the day ${event[0].durationInMinutes}")
    }
}