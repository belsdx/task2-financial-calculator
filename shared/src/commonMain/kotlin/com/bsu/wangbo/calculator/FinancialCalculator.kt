package com.bsu.wangbo.calculator

import kotlin.math.pow

// 定义复利频率
enum class CompoundingFrequency(val timesPerYear: Int) {
    MONTHLY(12),
    QUARTERLY(4),
    YEARLY(1)
}

data class CalculationResult(
    val finalAmount: Double,
    val totalProfit: Double
)

object FinancialCalculator {
    /**
     * 计算复利增长
     * A = P * (1 + r/n)^(nt)
     */
    fun calculateCompoundInterest(
        principal: Double,
        annualRate: Double, // 0.05 代表 5%
        years: Int,
        frequency: CompoundingFrequency
    ): CalculationResult {
        val n = frequency.timesPerYear.toDouble()
        val r = annualRate
        val t = years.toDouble()

        val amount = principal * (1 + r / n).pow(n * t)
        val profit = amount - principal

        // 保留两位小数
        return CalculationResult(
            finalAmount = (amount * 100).toInt() / 100.0,
            totalProfit = (profit * 100).toInt() / 100.0
        )
    }
}