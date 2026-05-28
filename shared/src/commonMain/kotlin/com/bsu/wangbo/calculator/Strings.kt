package com.bsu.wangbo.calculator

enum class Language { ZH, EN, RU }

data class AppStrings(
    val title: String,
    val principal: String,
    val rate: String,
    val years: String,
    val calc: String,
    val final: String,
    val profit: String
)

val stringsZh = AppStrings(
    title = "\u8d22\u52a1\u8ba1\u7b97\u5668",           // 财务计算器
    principal = "\u672c\u91d1",                       // 本金
    rate = "\u5e74\u5229\u7387 (\u5982 0.05)",        // 年利率 (如 0.05)
    years = "\u671f\u9650 (\u5e74)",                  // 期限 (年)
    calc = "\u8ba1\u7b97",                           // 计算
    final = "\u6700\u7ec8\u91d1\u989d: ",             // 最终金额:
    profit = "\u603b\u5229\u6da6: "                   // 总利润:
)

val stringsEn = AppStrings(
    title = "Finance Calculator",
    principal = "Principal",
    rate = "Annual Rate (e.g., 0.05)",
    years = "Years",
    calc = "Calculate",
    final = "Final Amount: ",
    profit = "Total Profit: "
)

val stringsRu = AppStrings(
    title = "Финансовый калькулятор",
    principal = "Основной капитал",
    rate = "Годовая ставка (напр. 0.05)",
    years = "Срок (лет)",
    calc = "Рассчитать",
    final = "Итоговая сумма: ",
    profit = "Общая прибыль: "
)