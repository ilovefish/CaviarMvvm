package com.ilovefish.caviarmvvm.network

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.ilovefish.caviarmvvm.base.appContext
import com.ilovefish.caviarmvvm.network.interceptor.CacheInterceptor
import com.ilovefish.caviarmvvm.network.interceptor.LogInterceptor
import com.ilovefish.caviarmvvm.network.interceptor.MyHeadInterceptor
import com.ilovefish.caviarmvvm.network.interceptor.TokenOutInterceptor
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 描述　: 网络请求构建器，继承BasenetworkApi 并实现setHttpClientBuilder/setRetrofitBuilder方法，
 * 在这里可以添加拦截器，设置构造器可以对Builder做任意操作
 */
class CaviarNetworkApi : BaseNetworkApi() {

    /**
     * 添加通用Head拦截器
     * */
    fun getHeadInterceptor(): Interceptor {
        return MyHeadInterceptor()
    }

    // 添加缓存通用拦截器
    fun getCacheInterceptor(): Interceptor {
        return CacheInterceptor();
    }

    // Token超时拦截器
    fun getTokenOutInterceptor(): Interceptor {
        return TokenOutInterceptor();
    }

    // 日志通用拦截器
    fun getLogInterceptor(): Interceptor {
        return LogInterceptor();
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(appContext.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
            cookieJar(cookieJar)
            try {
                //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
                addInterceptor(getHeadInterceptor())
                //添加缓存拦截器 可传入缓存天数，不传默认7天
                addInterceptor(getCacheInterceptor())
                addInterceptor(getTokenOutInterceptor())
                // 日志拦截器
                addInterceptor(getLogInterceptor())
            } catch (e: Exception) {
            }
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法，
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     */
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

    val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(appContext))
    }

}



