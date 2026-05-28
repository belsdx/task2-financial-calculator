package com.bsu.wangbo.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    // 获取平台信息
    val platformName = remember { getPlatform().name.lowercase() }

    MaterialTheme {
        // 使用 Surface 作为根容器，提供背景色
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF5F7FA)
        ) {
            // BoxWithConstraints 用于实现响应式布局，保证在 Web 大屏下不会拉伸过宽
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val isWideScreen = maxWidth > 600.dp

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 使用 Box 限制内容最大宽度，避免 Web 端拉伸过度
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        CalculatorScreen()
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // 平台标识
                    Text(
                        text = "Build for Multiplatform | Platform: ${platformName.uppercase()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}