package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import ru.geekbrains.lesson_1423_2_2_main.repository.DetailsRepositoryImpl
import ru.geekbrains.lesson_1423_2_2_main.repository.RemoteDataSource
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.utils.convertDtoToModel
import java.io.IOException
import java.lang.IllegalStateException

class DetailsViewModel(
    private val detailsLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepositoryImpl=   DetailsRepositoryImpl(RemoteDataSource())
) :
    ViewModel() {

    fun getLiveData() = detailsLiveDataToObserve;

    fun getWeatherFromRemoteSource(requestLink:String){
        detailsLiveDataToObserve.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(requestLink,callback)
    }
    private val callback =  object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // TODO HW   detailsLiveDataToObserve.postValue( AppState.Error("dfhgerh"))
        }

        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body?.string()
            if(response.isSuccessful&&serverResponse!=null){
                val weatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                detailsLiveDataToObserve.postValue( AppState.Success(convertDtoToModel(weatherDTO)))
            }else{
                // TODO HW   detailsLiveDataToObserve.postValue( AppState.Error("dfhgerh"))
            }
        }
    }


}