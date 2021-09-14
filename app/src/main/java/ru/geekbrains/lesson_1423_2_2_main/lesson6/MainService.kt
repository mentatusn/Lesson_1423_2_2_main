package ru.geekbrains.lesson_1423_2_2_main.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log

const val MAIN_SERVICE_STRING_EXTRA = "MainServiceExtra"

class MainService(name:String="name"):IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        createLogMessage("onHandleIntent ${intent?.getStringExtra(MAIN_SERVICE_STRING_EXTRA)}")
    }

    override fun onCreate() {
        createLogMessage("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createLogMessage("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        createLogMessage("onDestroy")
        super.onDestroy()
    }

    private fun createLogMessage(message: String) {
        //createLogMessage("createLogMessage")
        Log.d("mylogs", message)
    }

}