package com.example.unit3.collection

import org.junit.Test

class Solution {
    @Test
    fun listOfArray(){
        val arr= arrayListOf("huy","nguyen","vo")
        arr.let {
            println(arr.size)
            println(arr[1])
            println(arr.toString())
        }
    }
}