package com.example.calculator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _compute = MutableLiveData<Double>(0.0)
    val compute: LiveData<Double>
        get() = _compute
    private val _result = MutableLiveData<Double>(0.0)
    val result: LiveData<Double>
        get() = _result
    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String>
        get() = _expression
    private val _overFlow = MutableLiveData<Boolean>(false)
    val overFlow: LiveData<Boolean>
        get() = _overFlow

    fun ac(expression: String) {
        setCompute(0.0)
        setResult(0.0)
        setExpression(expression)
    }

    fun reverse(number: Double) {
        setCompute(number.times(-1))
    }

    fun factorial(number: Double) {
        if (number >= 1.0 && number < Double.MAX_VALUE) {
            var value = 1.0
            for (i in 1..number.toInt()) {
                value *= i
                if (isOverFlow(value))
                    return
            }
            setCompute(value)
        }
    }

    fun basicOperations(number: Double, expression: String) {
        compute(number, true)
        setExpression(expression)
    }

    fun equal(number: Double, expression: String) {
        compute(number, false)
        setCompute(_result.value)
        setResult(0.0)
        setExpression(expression)
    }

    private fun setResult(number: Double) {
        _result.value = number
    }

    private fun setCompute(number: Double?) {
        _compute.value = number
    }

    private fun setExpression(expression: String) {
        _expression.value = expression
    }

    private fun compute(number: Double, reset: Boolean) {
        var temp: Double? = when (_expression.value) {
            "+" -> _result.value?.plus(number)
            "-" -> _result.value?.minus(number)
            "×" -> _result.value?.times(if (number == 0.0) 1.0 else number)
            "÷" -> _result.value?.div(if (number == 0.0) 1.0 else number)
            else -> number
        }
        if (!isOverFlow(temp)) _result.value = temp
        if (reset) setCompute(0.0)
    }

    private fun isOverFlow(number: Double?): Boolean {
        if (number!! > Double.MAX_VALUE || number!! < -Double.MAX_VALUE) {
            _overFlow.value = true
            _overFlow.value = false
            return true
        }
        return false
    }
}