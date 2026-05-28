package com.bsu.wangbo.calculator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun GrowthChart(principal: Double, finalAmount: Double) {
    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)) {
        val width = size.width
        val height = size.height

        // 绘制灰色基准线
        drawLine(
            color = Color.Gray,
            start = Offset(0f, height),
            end = Offset(width, height),
            strokeWidth = 2f
        )

        // 动态计算：根据本金和最终金额的比例确定曲线终点高度
        // 防止除以0，确保 principal 大于 0
        val safePrincipal = if (principal <= 0) 1.0 else principal
        val ratio = (finalAmount / safePrincipal).coerceIn(1.0, 5.0) // 限制最大增长比例为5倍，防止超出屏幕
        val endY = height - (height * (ratio - 1.0) / 4.0).toFloat()

        // 绘制增长曲线
        val path = Path().apply {
            moveTo(0f, height)
            // 贝塞尔曲线，终点 y 坐标是动态计算的
            quadraticBezierTo(width * 0.5f, height * 0.8f, width, endY)
        }

        drawPath(
            path = path,
            color = Color(0xFF6200EE),
            style = Stroke(width = 5f)
        )
    }
}