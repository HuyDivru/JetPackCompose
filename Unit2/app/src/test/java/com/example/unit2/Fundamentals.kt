package com.example.unit2
import org.junit.Test
class Fundamentals {

    @Test
    fun main(){
        val morningNotification=51
        val eveningNotification=165

        printNotificationSummary(morningNotification)
        printNotificationSummary(eveningNotification)
    }

    fun printNotificationSummary(numberOfMessage:Int){
       if(numberOfMessage>99){
           println("Your phone is blowing up! You have 99+ notifications")
       }else{
           println("Your have $numberOfMessage notifications")
       }

    }

    // Movie ticker price
    fun ticketPrice(age:Int, isMonday:Boolean):Int{
//        var result=0;
//        if(age<18){
//            result=15
//        }
//        else if(age<60){
//            result=25
//        }
//        else{
//            result=20
//        }
//        return result
        return when {
            age <18 ->15
            age <60 ->25
            else ->20
        }
    }
    @Test
    fun movie(){
        val child=5
        val adult=28
        val senior=87
        val isMonday=true
        println("The movie ticker price for a person aged $child is \$${ticketPrice(child,isMonday)}")
        println("The movie ticker price for a person aged $adult is \$${ticketPrice(adult,isMonday)}")
        println("The movie ticker price for a person aged $senior is \$${ticketPrice(senior,isMonday)}")
    }


    //Temperature converter
    fun printFinalTemperature(intialMeasurment:Double,initialUnit:String,finalUnit:String,conversionFormula:(Double) -> Double){
        val finalMeasurement=String.format("%.2f",conversionFormula(intialMeasurment))
        println("$intialMeasurment degress $initialUnit is $finalMeasurement degress $finalUnit")
    }
    @Test
    fun temperature(){
        val celsius:String="Celsius"
        val fahrenheit:String="Fahrenheit"
        val kelvin:String="Kelvin"
        printFinalTemperature(27.0,celsius,fahrenheit, ::celsiusToFahrenheit)
        printFinalTemperature(350.0,kelvin,celsius, ::kelvinToCelsius)
        printFinalTemperature(10.0,fahrenheit,kelvin, ::fahrenheitToKelvin)
    }

    private fun fahrenheitToKelvin(d: Double): Double {
        return 5/9*(d-32) +273.15
    }

    private fun kelvinToCelsius(d: Double): Double {
        return d-273.15
    }

    private fun celsiusToFahrenheit(d: Double): Double {
        return  d*9/5+32
    }

    // Song catalog
    class Song(val title:String,val artist:String,val year:Int,var count:Int){
        var description:String =""
            public set
        //        set(value) {
//            println("The description of the song $value")
//            field=value
//        }
        fun property():String{
            return when {
                count>1000 ->"The song is popular"
                else -> "The song is unpopular"
            }
        }
    }
    @Test
    fun song(){
        val song=Song(title = "Chúng ta của tương lai","Sơn Tùng MTP",2024,3200000)

        println(song.property())
        song.description="Đừng Như con nit"
        println(song.description)
    }


    //Internet Profile
    class Person(val name:String, val age:Int,val hobby:String? ,val referrer:Person?){
        val person= if(referrer!=null) "Has a referrer named $name, who likes to play $hobby" else "Doesn't have a referrer"
        fun showProfile(){
            println("Name: $name\n Age: $age\n Likes to $hobby\t$person")
        }
    }
    @Test
    fun internet(){
        val amanda = Person("Amanda", 33, "play tennis", null)
        val atiqah = Person("Atiqah", 28, "climb", amanda)

        amanda.showProfile()
        atiqah.showProfile()
    }






    /*
    Special auction
    Typically in an auction, the highest bidder determines the price of an item. In this special auction, if there's no bidder for an item, the item is automatically sold to the auction house at the minimum price.
    In the initial code provided in the following code snippet, you're given an auctionPrice() function that accepts a nullable Bid? type as an argumen
     */
    class Bid(val amout:Int,val bidder:String)
    fun auctionPrice(bid:Bid?,minimunPrice:Int):Int{
//       return bid?.amout?.takeIf { it>minimunPrice } ?:minimunPrice
        return bid?.amout?:minimunPrice
    }

    @Test
    fun specialAuction(){
        val winningBid=Bid(5000,"Private Collector")
        println("Item A is sold at ${auctionPrice(winningBid,2000)}")
        println("Item B is sold at ${auctionPrice(null,3000)}")
    }
}