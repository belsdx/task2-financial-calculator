package com.bsu.wangbo.calculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform