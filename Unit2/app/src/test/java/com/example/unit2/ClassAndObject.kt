package com.example.unit2

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import org.junit.Test

import org.junit.Assert.*

class ClassAndObject {
    @Test
    fun test(){
        var smartTvDevice= SmartTvDevice("Android Tv","entertaiment")
       var smartLightDevice=SmartLightDevice("Google Light","Ultity")



        var smartHome=SmartHome(smartTvDevice,smartLightDevice)
        smartHome.turnOnTv()
        smartHome.turnOffTv()
        smartHome.turnOnLight()
        smartHome.turnOffAllDevices()
    }
}
open class SmartDevice(val name:String,val category: String){
    var deviceStatus="online"
    protected set
    open val deviceType="unknow";
    open fun turnOn(){
        deviceStatus="On"
    }
    open fun turnOff(){
        deviceStatus="Off"
    }
}
class SmartTvDevice(deviceName:String , deviceCategory:String):SmartDevice(name = deviceName, category = deviceCategory){
    override val deviceType="Smart Tv"
    private var speakerVolume by RangeRegulator(initialValue=2,minValue=0,maxValue=100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume(){
        speakerVolume++
        println("speaker volume increased to $speakerVolume")
    }
    fun nextChannel(){
        channelNumber++
        println("Channel number increased to $channelNumber")
    }

    override fun turnOn() {
        super.turnOn()
        println("$name is turned on. Speaker  volume is set to$speakerVolume and channel name $channelNumber")
    }

    override fun turnOff() {
        super.turnOff()
        println("$name is turned off.")
    }
}
class SmartLightDevice(deviceName: String,deviceCategory: String):SmartDevice(name=deviceName, category = deviceCategory){
    override val deviceType="Smart Tv"
    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)
    fun increaseBrightness(){
        brightnessLevel++
        println("BrigthNess increased to $brightnessLevel")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel=2
        println("$name turned on. The brightness level is $brightnessLevel")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel=0
        println("Smart Light turned off")
    }
}
class RangeRegulator(initialValue: Int, private val minValue: Int, private val  maxValue: Int):ReadWriteProperty<Any?,Int> {
    var fielData=initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fielData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if(value in minValue..maxValue){
            fielData=value
        }
    }

}
class SmartHome(val smartTvDevice: SmartTvDevice,val smartLightDevice: SmartLightDevice){
    var deviceTurnOnCount=0
    fun turnOnTv(){
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv(){
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseTvVolume(){
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext(){
        smartTvDevice.nextChannel()
    }
    fun turnOnLight(){
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight(){
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrightNess(){
        smartLightDevice.increaseBrightness()
    }
    fun turnOffAllDevices(){
        turnOffTv()
        turnOffLight()
    }
}