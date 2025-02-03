package com.vipul.kmp.news.utils
import platform.Foundation.NSUUID
import platform.UIKit.*

actual fun getType(): Type {
    return Type.Mobile
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}