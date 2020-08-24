package com.example.calculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FirstFragmentBinding

class FirstFragment: Fragment(){
    private lateinit var binding:FirstFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FirstFragmentBinding.inflate(inflater,container,false)
        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstToCalculator())
        }
        return binding.root
    }
}