package ru.geekbrains.lesson_1423_2_2_main.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.view.main.MainFragmentAdapter

class HistoryAdapter :RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var weatherData:List<Weather> = listOf()
    fun setWeather(data:List<Weather>){
        weatherData = data
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val holder = HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_history_recyclerview_item, parent, false)
        )
        return holder;
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount()= weatherData.size

    inner class HistoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun render(weather: Weather){
            itemView.findViewById<TextView>(R.id.recyclerViewItem).text =weather.city.name
        }
    }
}