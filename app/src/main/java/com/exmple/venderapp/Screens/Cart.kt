package com.exmple.venderapp.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exmple.venderapp.Model.CartItem
import com.exmple.venderapp.ViewModels.Vm

@Composable
fun Cart(viewmodel: Vm) {

    val cartList = viewmodel._cartItems
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(cartList.value) {
                CartItem(it)
            }
        }
        if (cartList.value!=null && cartList.value != emptyList<CartItem>()){
            Button(onClick = {
                cartList.value.forEach {
                    viewmodel.orderProduct(it.product_id, it.quantity)
                }
                Toast.makeText(context, "All Items Ordered !!", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Place Order")
            }
        }
    }
}
@Composable
private fun CartItem(cartItem: CartItem) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 3.dp),
    ) {
        Column(modifier = Modifier.padding(5.dp)){
            Text(text = "Product Name : ${cartItem.product_name} ", fontSize = 30.sp, fontFamily = FontFamily.SansSerif)
            Text(text = "Quantity : ${cartItem.quantity}", fontSize = 30.sp, fontFamily = FontFamily.SansSerif)
        }
    }
}