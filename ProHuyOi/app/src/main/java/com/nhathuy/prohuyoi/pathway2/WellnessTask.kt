package com.nhathuy.prohuyoi.pathway2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id:Int,
    val label:String,
    initialChecked:Boolean =false
) {
    var checked:Boolean by mutableStateOf(initialChecked)
}