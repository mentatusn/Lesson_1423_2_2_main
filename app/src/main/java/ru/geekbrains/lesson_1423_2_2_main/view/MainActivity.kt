package ru.geekbrains.lesson_1423_2_2_main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()

        // здесь вход в теоорию
        //val lesson = Lesson3()
        //lesson.mainSecondPart(this)

        // НЕВОЗМОЖНО var appState = AppState()
    }

}