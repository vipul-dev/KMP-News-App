package com.vipul.kmp.news.di

import com.vipul.kmp.news.utils.AppPreferences
import com.vipul.kmp.news.utils.dataStorePreference
import com.vipul.kmp.news.utils.getDatabaseBuilder
import com.vipul.kmp.news.utils.getRoomDatabase
import org.koin.dsl.module

val databaseModule = module {

    //database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    //data-store
    single {
        AppPreferences(dataStorePreference())
    }
}