package com.bsu.wangbo.calculator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FinancialCalculatorTest {

    @Test
    fun testCompoundInterestCalculation() {
        // 场景：本金 1000, 年利率 5%, 1年, 年复利
        val principal = 1000.0
        val rate = 0.05
        val years = 1
        val result = FinancialCalculator.calculateCompoundInterest(
            principal, rate, years, CompoundingFrequency.YEARLY
        )

        // 期望值：1000 * (1 + 0.05)^1 = 1050
        assertEquals(1050.0, result.finalAmount, 0.01)
        assertEquals(50.0, result.totalProfit, 0.01)
    }

    @Test
    fun testZeroRate() {
        // 利率为 0 时，本金应保持不变
        val result = FinancialCalculator.calculateCompoundInterest(
            1000.0, 0.0, 5, CompoundingFrequency.YEARLY
        )
        assertEquals(1000.0, result.finalAmount, 0.01)
        assertEquals(0.0, result.totalProfit, 0.01)
    }

    @Test
    fun testMonthlyCompounding() {
        // 测试按月复利
        val result = FinancialCalculator.calculateCompoundInterest(
            1000.0, 0.12, 1, CompoundingFrequency.MONTHLY
        )
        // 1000 * (1 + 0.12/12)^12 = 1000 * (1.01)^12 ≈ 1126.83
        assertTrue(result.finalAmount > 1126.0 && result.finalAmount < 1127.0)
    }
}