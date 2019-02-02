package fgcu.mangohacks2019;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApolloClient {

  private static final String BASE_URL="https://api.8base.com/cjrn2ja2s005501qjky8lphv3";
  private static ApolloClient myApolloClient;

  public static ApolloClient getMyApolloClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();
    myApolloClient = ApolloClient.builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .build();
    return myApolloClient;
  }

}
