package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.repository.RepositoryImpl
import java.lang.IllegalStateException
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    /*
    fun getLiveData(): LiveData<Any> {
        return liveDataToObserve;
    }
    */
    fun getLiveData() = liveDataToObserve;

    fun getWeatherFromLocalSourceWorld(){
        getDataFromLocalSource(false)
    }
    fun getWeatherFromLocalSourceRus(){
        getDataFromLocalSource(true)
    }

    private fun getDataFromLocalSource(isRussian:Boolean) {


        liveDataToObserve.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            if(isRussian){
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageRus()))
            }else{
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageWorld()))
            }

        }.start()
    }



}