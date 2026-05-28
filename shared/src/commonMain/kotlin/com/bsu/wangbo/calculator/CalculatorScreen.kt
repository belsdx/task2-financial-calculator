package com.bsu.wangbo.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorScreen() {
    var currentLang by remember { mutableStateOf(Language.ZH) }

    val strings = when (currentLang) {
        Language.ZH -> stringsZh
        Language.EN -> stringsEn
        Language.RU -> stringsRu
    }

    var principal by remember { mutableStateOf("1000") }
    var rate by remember { mutableStateOf("0.05") }
    var years by remember { mutableStateOf("1") }
    var calcResult by remember { mutableStateOf<CalculationResult?>(null) }

    // 使用 CompositionLocalProvider 全局注入 SansSerif，覆盖默认渲染字体
    CompositionLocalProvider(
        LocalTextStyle provides MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.SansSerif)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 语言切换
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Language.entries.forEach { lang ->
                    FilledTonalButton(onClick = { currentLang = lang }) {
                        Text(lang.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.SansSerif)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 输入框
            OutlinedTextField(
                value = principal, onValueChange = { principal = it },
                label = { Text(strings.principal) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = rate, onValueChange = { rate = it },
                label = { Text(strings.rate) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = years, onValueChange = { years = it },
                label = { Text(strings.years) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val p = principal.toDoubleOrNull() ?: 1000.0
                    val r = rate.toDoubleOrNull() ?: 0.05
                    val y = years.toIntOrNull() ?: 1
                    calcResult = FinancialCalculator.calculateCompoundInterest(
                        p, r, y, CompoundingFrequency.YEARLY
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(strings.calc)
            }

            Spacer(modifier = Modifier.height(24.dp))

            calcResult?.let { result ->
                Text(
                    text = "${strings.final}${result.finalAmount}",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.SansSerif)
                )
                Text(text = "${strings.profit}${result.totalProfit}")

                Spacer(modifier = Modifier.height(16.dp))

                GrowthChart(
                    principal = principal.toDoubleOrNull() ?: 1000.0,
                    finalAmount = result.finalAmount
                )
            }
        }
    }
}