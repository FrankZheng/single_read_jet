package com.xzheng.singlereadjet.network

import com.squareup.moshi.Json

data class ArticlesResponse(
    val status: String,
    @Json(name = "datas") val data: List<Article>
)