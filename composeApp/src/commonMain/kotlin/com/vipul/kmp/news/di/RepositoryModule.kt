package com.vipul.kmp.news.di

import com.vipul.kmp.news.database.NewsDatabase
import com.vipul.kmp.news.repository.LocalNewsRepository
import com.vipul.kmp.news.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        OnlineNewsRepository(get())
    }

    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
}