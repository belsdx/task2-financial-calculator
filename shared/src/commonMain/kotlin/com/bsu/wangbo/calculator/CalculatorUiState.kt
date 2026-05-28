package com.bsu.wangbo.calculator

data class CalculatorUiState(
    val principal: String = "1000",
    val rate: String = "0.05",
    val years: String = "1",
    val result: CalculationResult? = null
)