package com.mykotlinapplication.calculator

import androidx.core.text.isDigitsOnly
import java.lang.StringBuilder

fun main () {
    val s = StringBuilder()
    s.append("10.8+-*/")
//    var temp = s.substring(0, 2)
//    if (s.matches("-?\\d+(\\.\\d+)?".toRegex())) {
    if (s.contains("+")) {
        println("true")
    } else {
        println("false")
    }

}