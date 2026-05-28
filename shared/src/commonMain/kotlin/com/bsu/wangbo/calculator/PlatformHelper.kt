package com.bsu.wangbo.calculator

// 定义平台枚举
enum class CurrentPlatform { ANDROID, IOS, DESKTOP, WEB }

// 全局单例，存储当前平台信息
object PlatformHelper {
    var current: CurrentPlatform = CurrentPlatform.DESKTOP // 默认值
}