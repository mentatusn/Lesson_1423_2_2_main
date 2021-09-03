package ru.geekbrains.lesson_1423_2_2_main.lesson3

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Lesson3 {

    fun mainFirstPart() {
        val bar = AppCompatActivity().getSupportActionBar()
        val menu = AppCompatActivity().menuInflater
        val appNotNullable: Application =  AppCompatActivity().application
        val appNullable: Application? =  AppCompatActivity().application
        var notNullable: String = ""
        val testObj: Test? = if(Random(2).nextInt()>0) Test() else null
        var nullable: String? = ""
        notNullable = testObj?.stringTest ?: ""
        notNullable = if(testObj?.stringTest!=null){
            testObj.stringTest
        }else{
            ""
        }
        //выстрелим себе в ногу nullpointerexception
        notNullable = testObj!!.stringTest
        nullable = null
        if (nullable != null) {
            nullable!!

        }
        if (nullable != null) {

        }
    }

    
}


class Test {
    val stringTest:String = "test"
}