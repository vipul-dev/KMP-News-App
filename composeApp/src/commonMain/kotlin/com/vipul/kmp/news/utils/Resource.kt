package com.vipul.kmp.news.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

sealed class Resource<out T> {
    data object Idle : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (String) -> Unit,
    ) {
        AnimatedContent(
            targetState = this,
            transitionSpec = {
                FadeIN togetherWith FadeOUT
            },
            label = "Content Animation"
        ) {
            when (it) {
                is Idle -> onIdle?.invoke()
                is Loading -> onLoading()
                is Success -> onSuccess(it.data)
                is Error -> onError(it.message)
            }
        }
    }
}