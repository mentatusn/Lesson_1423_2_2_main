package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) :
    ViewModel() {

    /*
    fun getLiveData(): LiveData<Any> {
        return liveDataToObserve;
    }
    */
    fun getLiveData()= liveDataToObserve;

    fun getDataFromRemoteSource(){
        Thread {
            sleep(2000)
            liveDataToObserve.postValue(Any())
        }.start()
    }

}