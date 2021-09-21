package ru.geekbrains.lesson_1423_2_2_main.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.lesson_1423_2_2_main.MyApp.Companion.getHistoryDAO
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.ActivityMainBinding
import ru.geekbrains.lesson_1423_2_2_main.lesson6.ThreadsFragment
import ru.geekbrains.lesson_1423_2_2_main.view.history.HistoryFragment
import ru.geekbrains.lesson_1423_2_2_main.view.main.MainFragment


class MainActivity : AppCompatActivity() {




    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_open_fragment_threads ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ThreadsFragment.newInstance()).commit()
                true
            }

            R.id.action_open_fragment_history ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HistoryFragment.newInstance()).addToBackStack("").commit()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }


}