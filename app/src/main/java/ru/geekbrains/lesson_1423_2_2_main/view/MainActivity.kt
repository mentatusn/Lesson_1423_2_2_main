package ru.geekbrains.lesson_1423_2_2_main.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main_webview.*
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.ActivityMainBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.ActivityMainWebviewBinding
import ru.geekbrains.lesson_1423_2_2_main.view.main.MainFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
    }


}