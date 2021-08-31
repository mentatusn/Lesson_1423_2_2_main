package ru.geekbrains.lesson_1423_2_2_main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.ActivityMainBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentMainBinding
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.MainViewModel

class MainFragment: Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding:FragmentMainBinding
    get(){
        return  _binding!!
    }


    private lateinit var viewModel:MainViewModel

    companion object{
        /*
        fun newInstance():Fragment{
            return MainFragment()
        }
        */
        fun newInstance()= MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner,Observer<Any>{
            Toast.makeText(context,"its work",Toast.LENGTH_LONG).show()
        })
        viewModel.getDataFromRemoteSource()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
