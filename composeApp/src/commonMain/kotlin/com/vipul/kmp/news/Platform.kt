package com.vipul.kmp.news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform