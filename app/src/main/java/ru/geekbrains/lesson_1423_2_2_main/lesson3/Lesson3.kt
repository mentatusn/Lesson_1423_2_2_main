package ru.geekbrains.lesson_1423_2_2_main.lesson3

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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


    fun mainSecondPart(context: Context){
        val phrase= arrayOf("first","second")
        val word = phrase[1]
        phrase[1] = "secondNew"
        phrase.size


        val people:List<Person> = listOf(Person("Максим",25),Person("Оля",20))
        val ant = mutableListOf(Person("Максим",25),"")
        people[0].age=26
        (ant[0] as Person).age = 26
        ant[1] = "26"

        val peopleHack:MutableList<Person> = people.toMutableList()
        peopleHack.add(Person("Петя",0))

        val myInt:Int =2
        Log.d("mylogs","${myInt.mySquare()} $myInt")


        write(1);
        write(1.0);
        write("");
        write(1f);
        write(people[0]);

        writeInt(1);
        writeDouble(1.0);
        writeString("");
        writeFloat(1f);
        writePerson(people[0]);

        val btn  =Button(context)
        val layout  =LinearLayout(context)
        val view1 = Generic2(layout)
    }
    data class Person(val name:String, var age:Int)
    private fun writeInt(input:Int) = Log.d("mylogs",input.toString())
    private fun writeDouble(input:Double) = Log.d("mylogs",input.toString())
    private fun writeString(input:String) = Log.d("mylogs",input.toString())
    private fun writeFloat(input:Float) = Log.d("mylogs",input.toString())
    private fun writePerson(input:Person) = Log.d("mylogs",input.toString())

    fun <T> write(input: T) =Log.d("mylogs",input.toString())
    fun <T,G,J> writeTriple(input1: T,input2: G,input3: J) =Log.d("mylogs",input1.toString())

    fun Int.mySquare():Int{
        return this*this;
    }

    class Generic<Type1>(val field1:Type1)
    class Generic2<T:ViewGroup>(val field1:T)
}

class Test {
    val stringTest:String = "test"
}
interface Test3 {
    fun withImpl():String{
        //что происходит
        return ""
    }
    fun withoutImpl():String
    val greeting: String
        get() = "Hello from the air!"
}
