package com.vipul.kmp.news.di

import com.vipul.kmp.news.ui.articleDetail.ArticleDetailsViewModel
import com.vipul.kmp.news.ui.bookmark.BookmarkViewmodel
import com.vipul.kmp.news.ui.headline.HeadlineViewmodel
import com.vipul.kmp.news.ui.search.SearchViewmodel
import com.vipul.kmp.news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    factoryOf(::HeadlineViewmodel)
    factoryOf(::SearchViewmodel)
    factoryOf(::BookmarkViewmodel)
    factoryOf(::ArticleDetailsViewModel)
    factoryOf(::SettingViewModel)
}