package com.exmple.venderapp

import android.util.Log
import com.exmple.venderapp.Model.GetAllProduct
import com.exmple.venderapp.Pref.UserIdPrefImp
import com.exmple.venderapp.RetroFit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Repo(val userIdPrefImp: UserIdPrefImp) {

  private  val _products :MutableStateFlow<List<GetAllProduct>> = MutableStateFlow<List<GetAllProduct>>(emptyList())
    val product :StateFlow<List<GetAllProduct>>
        get() = _products

    suspend fun AllProduct(){
        val Products = RetrofitInstance.api.getAllProduct()
        if (Products.isSuccessful && Products.body()!=null){
            Log.d("VisRepo","${Products.body()}")
            _products.emit(Products.body()!!)
        }
    }
    suspend fun OrderProduct(productid:Int,stock:Int){
        val response = RetrofitInstance.api.orderProduct(productid,stock)
    }



    suspend fun setIdInPref(id:String){
        userIdPrefImp.setId(id)
    }

    fun getId(): Flow<String> {
        return userIdPrefImp.getId()
    }

    suspend fun LoginUser(email :String,password:String){
        val response = RetrofitInstance.api.LoginUser(email, password)
        if (response.isSuccessful && response.body()!=null && response.body()!!.success == 200){
          setIdInPref(response!!.body()!!.message)
        }
        else{
            Log.d("User_Id","${response.body()!!.message}")
        }
    }
 }