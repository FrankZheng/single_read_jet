package com.xzheng.singlereadjet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://static.owspace.com/?c=api&a=getList&p=1&model=1&page_id=0&create_time=0&client=android&version=1.3.0&time=1467867330&device_id=866963027059338&show_sdv=1"



interface SingleReadApiService {
    @GET("/")
    suspend fun getArticles(
            @Query("p") page:Int,
            @Query("model") model:Int,
            @Query("c") c:String = "api",
            @Query("a") a:String = "getList",
            @Query("page_id") pageId:Int = 0,
            @Query("create_time") createTime:Int = 0,
            @Query("client") client:String = "android",
            @Query("version") version:String = "1.3.0",
            @Query("time") time:Int = 1467867330,
            @Query("device_id") deviceId:String = "866963027059338",
            @Query( "show_sdv") showSdv:Int = 1,
    ): ArticlesResponse
}

object SingleReadApi {
    val instance: SingleReadApiService by lazy {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build()

        retrofit.create(SingleReadApiService::class.java)
    }
}