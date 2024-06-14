package com.exmple.venderapp.RetroFit

import com.exmple.venderapp.Model.CreateUserApi
import com.exmple.venderapp.Model.GetAllProduct
import com.exmple.venderapp.Model.OrderProduct
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "https://vishal8851.pythonanywhere.com"

interface ApiService {

    @FormUrlEncoded
    @POST("/createUser")
    suspend fun createUser(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("pincode") pincode: String,
    ): Response<CreateUserApi>

    @GET("/getAllProducts")
    suspend fun getAllProduct(): Response<List<GetAllProduct>>

    @FormUrlEncoded
    @POST("/updatestock")
    suspend fun orderProduct(
        @Field("product_id") productid: Int,
        @Field("stock") stock: Int,
    ): Response<OrderProduct>

    @FormUrlEncoded
    @POST("/loginUser")
    suspend fun LoginUser(
        @Field("email") email:String,
        @Field("password") password:String,
    ): Response<OrderProduct>
}

object RetrofitInstance {

    val client = OkHttpClient.Builder().build()
    val api = Retrofit.Builder().client(client).baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiService::class.java)
}