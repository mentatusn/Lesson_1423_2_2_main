package ru.geekbrains.lesson_1423_2_2_main.lesson4

import android.util.Log

class LambdaKotlin {
    fun main(){
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
        

    }

    //2 часть начинается
    val valAnonymous = fun(int1:Int,int2:Int):String{
        Log.d("mylogs", " Зашли в funAnonymous")
        return "funAnonymous"
    }

    val valLambda = hack@{int1:Int,int2:Int ->
        Log.d("mylogs", " Зашли в funLambda")
        return@hack "funLambda"
    }

    fun printMy(fun1:(int1:Int,int2:Int)->String){
        Log.d("mylogs", " Зашли в printMy")
        Log.d("mylogs", fun1(1,2))
    }
    //2 часть заканчивается
}