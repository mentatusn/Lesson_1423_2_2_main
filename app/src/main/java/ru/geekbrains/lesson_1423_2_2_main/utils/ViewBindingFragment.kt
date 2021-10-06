package ru.geekbrains.lesson_1423_2_2_main.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentDetailsBinding

abstract class ViewBindingFragment<T : ViewBinding>(
    private val inflateBinding: (inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    val binding: T
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding.invoke(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

