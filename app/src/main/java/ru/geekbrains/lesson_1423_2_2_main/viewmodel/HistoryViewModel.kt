package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lesson_1423_2_2_main.MyApp
import ru.geekbrains.lesson_1423_2_2_main.repository.DetailsRepositoryImpl
import ru.geekbrains.lesson_1423_2_2_main.repository.LocalRepositoryImpl
import ru.geekbrains.lesson_1423_2_2_main.repository.RemoteDataSource
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.utils.convertDtoToModel

class HistoryViewModel(
    private val historyLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepositoryImpl: LocalRepositoryImpl = LocalRepositoryImpl(MyApp.getHistoryDAO())
) :
    ViewModel() {
    fun getAllHistory() {
        historyLiveDataToObserve.value = AppState.Loading
        historyLiveDataToObserve.postValue(AppState.SuccessMain(historyRepositoryImpl.getAllHistory()))

    }
    fun getLiveData() = historyLiveDataToObserve;
}