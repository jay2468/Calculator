package com.example.calculator.module

import com.example.calculator.viewModel.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    viewModel { CalculatorViewModel() }
}