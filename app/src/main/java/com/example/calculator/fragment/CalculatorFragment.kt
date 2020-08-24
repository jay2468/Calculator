package com.example.calculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.calculator.R
import com.example.calculator.databinding.CalculatorFragmentBinding
import com.example.calculator.viewModel.CalculatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class CalculatorFragment : Fragment() {
    private lateinit var binding: CalculatorFragmentBinding
    private val viewModel: CalculatorViewModel by viewModel()
    private val scientific: DecimalFormat by lazy { DecimalFormat("0.#####E0") }
    private val decimalFormat: DecimalFormat by lazy { DecimalFormat("0.#####") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.calculator_fragment, container, false)
        binding.viewModel = viewModel
        with(viewModel) {
            result.observe(viewLifecycleOwner, Observer {
                binding.tvResult.text = if (it != 0.0) format(it) else ""
            })

            expression.observe(viewLifecycleOwner, Observer {
                binding.tvExpression.text = it
                if (it.isNotEmpty())
                    binding.etNumber.setText("")
            })

            compute.observe(viewLifecycleOwner, Observer {
                binding.etNumber.setText(format(it))
            })

            overFlow.observe(viewLifecycleOwner, Observer {
                if (it) {
                    Toast.makeText(context, getString(R.string.overflow), Toast.LENGTH_SHORT).show()
                }
            })
        }
        return binding.root
    }

    private fun format(num: Double): String {
        if (num < 10000000000.0 && num > -10000000000.0)
            return decimalFormat.format(num)
        return scientific.format(num)
    }
}