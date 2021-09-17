package ru.geekbrains.lesson_1423_2_2_main.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.show(text:String, actionText:String, action: View.OnClickListener){
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).setAction(actionText,action).show()
}