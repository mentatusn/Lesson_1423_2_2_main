package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lesson_1423_2_2_main.repository.DetailsRepositoryImpl
import ru.geekbrains.lesson_1423_2_2_main.repository.RemoteDataSource
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.utils.convertDtoToModel

class DetailsViewModel(
    private val detailsLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepositoryImpl=   DetailsRepositoryImpl(RemoteDataSource())
) :
    ViewModel() {

    fun getLiveData() = detailsLiveDataToObserve;

    fun getWeatherFromRemoteSource(lat:Double,log:Double){
        detailsLiveDataToObserve.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(lat,log,callback)
    }
    private val callback =  object : retrofit2.Callback<WeatherDTO> {


        override fun onResponse(call: retrofit2.Call<WeatherDTO>, response: retrofit2.Response<WeatherDTO>) {

            if(response.isSuccessful&&response.body()!=null){
                val weatherDTO = response.body()
                weatherDTO?.let{
                    detailsLiveDataToObserve.postValue( AppState.SuccessDetails(convertDtoToModel(weatherDTO)))
                }
            }else{
                // TODO HW   detailsLiveDataToObserve.postValue( AppState.Error("dfhgerh"))
            }
        }

        override fun onFailure(call: retrofit2.Call<WeatherDTO>, t: Throwable) {
            // TODO HW   detailsLiveDataToObserve.postValue( AppState.Error("dfhgerh"))
        }
    }


}