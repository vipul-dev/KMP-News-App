package com.vipul.kmp.news.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vipul.kmp.news.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun newsDao(): NewsDao
}

interface DB {
    fun clearAllTables(): Unit {

    }
}