package com.exmple.venderapp.ViewModels
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exmple.venderapp.Model.CartItem
import com.exmple.venderapp.Model.GetAllProduct
import com.exmple.venderapp.Pref.UserIdPrefImp
import com.exmple.venderapp.Repo
import com.exmple.venderapp.RetroFit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Vm(val context: Context):ViewModel() {


    val datastore = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler (produceNewData = { emptyPreferences() }),
        produceFile = {context.preferencesDataStoreFile("userPref")}
    )

    val vendorRepo = Repo(UserIdPrefImp(datastore))
    val _cartItems: MutableState<MutableList<CartItem>> = mutableStateOf(mutableListOf<CartItem>())
    fun setCartItem(productId: Int, productName: String, quantity: Int) {
        val newItem = CartItem(productId, quantity, productName)
        _cartItems.value.add(newItem)

    }

    init {
        viewModelScope.launch {
            vendorRepo.AllProduct()
        }
        Log.d("VisVm","${vendorRepo.product}")
    }

    var productList: StateFlow<List<GetAllProduct>> = vendorRepo.product

    fun orderProduct(product_id:Int,stock:Int){
        viewModelScope.launch{
            vendorRepo.OrderProduct(product_id, stock)
        }
    }

    fun getid():Flow<String>{
        return vendorRepo.getId()
    }



    fun LoginUser(email:String , password:String){
        viewModelScope.launch {
           vendorRepo.LoginUser(email, password)
        }
    }


    // New Part Added

    var state: MutableState<String> = mutableStateOf("")
    init{
        state.value = com.exmple.venderapp.ViewModels.State.DEFAULT.name
    }

    fun createUser(name:String,password:String , address:String,email:String,phone:String,pincode:String){
        state.value =com.exmple.venderapp.ViewModels.State.LOADING.name
        viewModelScope.launch {
            var result = RetrofitInstance.api.createUser(name, password , address,email,phone,pincode)
            if(result.isSuccessful==true){
                state.value = com.exmple.venderapp.ViewModels.State.SUCCESS.name
                if(result.body()?.success==200){
                    state.value = com.exmple.venderapp.ViewModels.State.SUCCESS.name
                }
            }
            else{
                state.value = com.exmple.venderapp.ViewModels.State.Failed.name
            }
        }
    }
}


sealed class State(var name:String){

    object DEFAULT:com.exmple.venderapp.ViewModels.State("default")
    object Failed:com.exmple.venderapp.ViewModels.State("failed")
    object SUCCESS:com.exmple.venderapp.ViewModels.State("success")
    object LOADING:com.exmple.venderapp.ViewModels.State("loading")

}