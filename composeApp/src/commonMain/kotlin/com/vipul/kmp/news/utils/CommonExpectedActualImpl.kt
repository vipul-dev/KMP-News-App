package com.vipul.kmp.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vipul.kmp.news.database.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

expect fun getType(): Type

expect fun randomUUIDStr(): String

expect fun shareLink(url: String)

expect fun dataStorePreference(): DataStore<Preferences>

object AppSettings {
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalCoroutinesApi::class)
    private val lock = SynchronizedObject()

    @OptIn(InternalCoroutinesApi::class)
    fun getDataStore(producerPath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producerPath().toPath() }).also {
                    dataStore = it
                }

            }
        }
    }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>

fun getRoomDatabase(builder: RoomDatabase.Builder<NewsDatabase>): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}