package id.android.training

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import id.android.training.data.MainService
import id.android.training.domain.RemoteMainRepository
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit.SECONDS

private const val BASE_URL = "https://yg18k7js4m.execute-api.ap-southeast-3.amazonaws.com"

interface AppContainer {

  val repository: RemoteMainRepository
}

class MainAppContainer(application: Application) : AppContainer {

  private val cache =
    Cache(
      directory = application.cacheDir,
      maxSize = 30.times(1024).times(1024)
    )

  private val connectionPool =
    ConnectionPool(
      maxIdleConnections = 15,
      keepAliveDuration = 30.times(1000),
      timeUnit = SECONDS
    )

  private val httpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = BODY }

  private val httpClient =
    OkHttpClient.Builder().apply {
      connectTimeout(30, SECONDS)
      readTimeout(15, SECONDS)
      writeTimeout(15, SECONDS)
      retryOnConnectionFailure(true)
      cache(cache)
      connectionPool(connectionPool)
      addInterceptor(httpLoggingInterceptor)
    }.build()

  private val retrofit =
    Retrofit.Builder().apply {
      client(httpClient)
      baseUrl(BASE_URL)
      addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    }.build()

  private val mainService: MainService by lazy {
    retrofit.create(MainService::class.java)
  }

  override val repository: RemoteMainRepository by lazy {
    RemoteMainRepository(service = mainService)
  }
}