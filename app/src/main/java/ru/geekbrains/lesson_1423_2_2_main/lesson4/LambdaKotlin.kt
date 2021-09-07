package ru.geekbrains.lesson_1423_2_2_main.lesson4

import android.app.Person
import android.util.Log
import ru.geekbrains.lesson_1423_2_2_main.lesson3.Lesson3

class LambdaKotlin {
    fun main() {
        //1 часть
        /*val l1 = { Log.d("mylogs", "run1")
            "run2"}
        val l2 =  run { Log.d("mylogs", "run3")
             "run4"}
        Log.d("mylogs", l1())
        Log.d("mylogs", l2)*/

        //2 часть начинается
        printMy(valAnonymous)
        printMy(valLambda)


        // 3 часть
        val l3 = { int1: Int, int2: Int ->
            Log.d("mylogs", " 1")
            Log.d("mylogs", " 2")
            Log.d("mylogs", " 3")
            Unit
        }
        val l4 = l3(1, 3)

        // 4 часть


        val people: List<Person> = listOf(Person("name1", 10), Person("name2", 20))

        /*people.forEach({ person: Person -> print(person) })
        people.forEach() { person: Person -> Log.d("mylogs", "${person.name}") }
        people.forEach { person: Person -> Log.d("mylogs", "${person.name}") }
        people.forEach { person -> Log.d("mylogs", "${person.name}") }
        people.forEach { Log.d("mylogs", "${it.name}") }*/
        people.forEach { it.copy() }
        people.forEach { p2(it) }


        val testObj = Test()
        test(testObj)
    }
    private fun test(testObj:Test){
        Log.d("mylogs","ext")
    }
    class Test{
        fun test(){
            Log.d("mylogs","inner")
        }
    }


    data class Person(var name: String, val age: Int)
    fun p2(person:Person){
        Log.d("mylogs", "${person.name} ${person.age}")
    }


    //2 часть начинается
    private val valAnonymous = fun(int1: Int, int2: Int): String {
        Log.d("mylogs", " Зашли в funAnonymous")
        return "funAnonymous"
    }

    private val valLambda = hack@{ int1: Int, int2: Int ->
        Log.d("mylogs", " Зашли в funLambda")
        return@hack "funLambda"
    }

    private fun printMy(fun1: (int1: Int, int2: Int) -> String) {
        Log.d("mylogs", " Зашли в printMy")
        Log.d("mylogs", fun1(1, 2))
    }
    //2 часть заканчивается
}

fun LambdaKotlin.Person.print2():String{
    Log.d("mylogs", "${this.name} ${this.age}")
    return "на тебе ретурн"
}