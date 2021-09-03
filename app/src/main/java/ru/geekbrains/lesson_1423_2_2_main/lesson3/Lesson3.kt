package ru.geekbrains.lesson_1423_2_2_main.lesson3

import android.app.Application
import android.util.Log
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


    fun mainSecondPart(){
        val phrase= arrayOf("first","second")
        val word = phrase[1]
        phrase[1] = "secondNew"
        phrase.size

        class Person(val name:String, var age:Int)
        val people:List<Person> = listOf(Person("Максим",25),Person("Оля",20))
        val ant = mutableListOf(Person("Максим",25),"")
        people[0].age=26
        (ant[0] as Person).age = 26
        ant[1] = "26"

        val peopleHack:MutableList<Person> = people.toMutableList()
        peopleHack.add(Person("Петя",0))

        var myInt:Int =2
        Log.d("mylogs","${myInt.mySquare()} $myInt")
    }

    fun Int.mySquare():Int{
        return this*this;
    }
}

class Test {
    val stringTest:String = "test"
}