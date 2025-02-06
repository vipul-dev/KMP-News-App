package com.vipul.kmp.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun getType(): Type {
    return Type.Desktop
}

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url:String){
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url),null)
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}