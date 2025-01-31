@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package kmp_news_app.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.StringResource

private object CommonMainString0 {
  public val app_name: StringResource by 
      lazy { init_app_name() }

  public val bookmark: StringResource by 
      lazy { init_bookmark() }

  public val headlines: StringResource by 
      lazy { init_headlines() }

  public val search: StringResource by 
      lazy { init_search() }

  public val setting: StringResource by 
      lazy { init_setting() }
}

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("app_name", CommonMainString0.app_name)
  map.put("bookmark", CommonMainString0.bookmark)
  map.put("headlines", CommonMainString0.headlines)
  map.put("search", CommonMainString0.search)
  map.put("setting", CommonMainString0.setting)
}

internal val Res.string.app_name: StringResource
  get() = CommonMainString0.app_name

private fun init_app_name(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:app_name", "app_name",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kmp_news_app.composeapp.generated.resources/values/strings.commonMain.cvr",
    10, 32),
    )
)

internal val Res.string.bookmark: StringResource
  get() = CommonMainString0.bookmark

private fun init_bookmark(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:bookmark", "bookmark",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kmp_news_app.composeapp.generated.resources/values/strings.commonMain.cvr",
    43, 28),
    )
)

internal val Res.string.headlines: StringResource
  get() = CommonMainString0.headlines

private fun init_headlines(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:headlines", "headlines",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kmp_news_app.composeapp.generated.resources/values/strings.commonMain.cvr",
    72, 29),
    )
)

internal val Res.string.search: StringResource
  get() = CommonMainString0.search

private fun init_search(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:search", "search",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kmp_news_app.composeapp.generated.resources/values/strings.commonMain.cvr",
    102, 22),
    )
)

internal val Res.string.setting: StringResource
  get() = CommonMainString0.setting

private fun init_setting(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:setting", "setting",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kmp_news_app.composeapp.generated.resources/values/strings.commonMain.cvr",
    125, 27),
    )
)
