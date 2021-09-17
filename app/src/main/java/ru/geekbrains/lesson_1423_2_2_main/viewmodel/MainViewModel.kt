package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lesson_1423_2_2_main.repository.RepositoryImpl
import java.lang.Thread.sleep

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
                liveDataToObserve.postValue(AppState.SuccessMain(repositoryImpl.getWeatherFromLocalStorageRus()))
            }else{
                liveDataToObserve.postValue(AppState.SuccessMain(repositoryImpl.getWeatherFromLocalStorageWorld()))
            }

        }.start()
    }



}