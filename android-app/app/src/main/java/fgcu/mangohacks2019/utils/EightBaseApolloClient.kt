package fgcu.mangohacks2019.utils

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class EightBaseApolloClient {

  private val BASE_URL = "https://api.8base.com/cjrn2ja2s005501qjky8lphv3"
  private var apolloClient: ApolloClient? = null

  fun getEightBaseApolloClient(): ApolloClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    apolloClient = ApolloClient.builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .build()
    return apolloClient!!
  }
}