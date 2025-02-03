package com.vipul.kmp.news.utils

import java.util.UUID

actual fun getType(): Type {
    return Type.Desktop
}

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}