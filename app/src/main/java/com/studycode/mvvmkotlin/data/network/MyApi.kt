package com.studycode.mvvmkotlin.data.network

import com.google.gson.GsonBuilder
import com.studycode.mvvmkotlin.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

interface MyApi {


    @FormUrlEncoded
    @POST("login")
   suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): retrofit2.Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(@Field("name") name:String,
                   @Field("email") email: String,
                   @Field("password") password: String):retrofit2.Response<AuthResponse>


    companion object {

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
//                .baseUrl("https://xyz-chama-api.herokuapp.com/")
                .addConverterFactory(ApiWorker.gsonConverter)
                .client(ApiWorker.client)
                .build()
                .create(MyApi::class.java)
        }
    }


    object ApiWorker {
        private var mClient: OkHttpClient? = null
        private var mGsonConverter: GsonConverterFactory? = null
        val client: OkHttpClient
            @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
            get() {
                if (mClient == null) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY

                    val httpBuilder = OkHttpClient.Builder()
                    httpBuilder

                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(interceptor) // show all JSON in logCat

                    mClient = httpBuilder.build()

                }
                return mClient!!
            }

        val gsonConverter: GsonConverterFactory
            get() {
                if (mGsonConverter == null) {
                    mGsonConverter = GsonConverterFactory
                        .create(
                            GsonBuilder()
                                .setLenient()
                                .disableHtmlEscaping()
                                .create()
                        )
                }
                return mGsonConverter!!
            }

    }


}