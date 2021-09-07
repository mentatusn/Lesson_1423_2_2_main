package ru.geekbrains.lesson_1423_2_2_main.lesson4

import android.util.Log
import ru.geekbrains.lesson_1423_2_2_main.lesson3.Lesson3
import ru.geekbrains.lesson_1423_2_2_main.lesson4.*

class MyExtension {
    fun main(){
        val people: List<LambdaKotlin.Person> = listOf(
            LambdaKotlin.Person("name1", 10),
            LambdaKotlin.Person("name2", 20)
        )
        people.indexOf(people[0])
        people.indexOf(people[1])
        people[0].name
        people[0].age

        run{

        }
        val withRes =with(people){
            get(0).name = "withname1"
            get(1).name = "withname2"
        }
        val alsoRes = people.also {
            it.get(0).name = "applyname1"
            it.get(1).name = "applyname2"
        }

        val applyRes = people.apply {
            get(0).name = "applyname1"
            get(1).name = "applyname2"
        }

        //applyRes.forEach {  (it.print2()) }
        //alsoRes.forEach {  (it.print2()) }

        var x = 20
        var y = 30
        //x = y.also{ y=x}
        x = y.apply{ y=x}
        //Log.d("mylogs", "x=${x}  y=${y}")


        var person:LambdaKotlin.Person? =null
        person = LambdaKotlin.Person("no-null",12)
        if(person!=null){
            //person.print2()
        }
        person?.let{
            it.print2()
        }

    }
}