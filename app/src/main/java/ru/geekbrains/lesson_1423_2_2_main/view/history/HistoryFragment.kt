package ru.geekbrains.lesson_1423_2_2_main.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentHistoryBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentMainBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.view.OnItemViewClickListener
import ru.geekbrains.lesson_1423_2_2_main.view.details.DetailsFragment
import ru.geekbrains.lesson_1423_2_2_main.view.main.MainFragmentAdapter
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.AppState
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.HistoryViewModel
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.MainViewModel

class HistoryFragment : Fragment() {


    private var _binding: FragmentHistoryBinding? = null
    private val binding: FragmentHistoryBinding
        get() {
            return _binding!!
        }
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }


    private val viewModel:HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                val throwable = appState.error
                Snackbar.make(binding.root, "ERROR $throwable", Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.SuccessMain -> {
                binding.historyFragmentRecyclerview.adapter = adapter
                binding.loadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
                Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
