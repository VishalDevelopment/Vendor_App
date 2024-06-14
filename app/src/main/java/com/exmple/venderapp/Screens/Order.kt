package com.exmple.venderapp.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.exmple.venderapp.ViewModels.Vm

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Order(viewmodel: Vm) {

    val productList = viewmodel.productList.collectAsState(initial = emptyList())

    var product_id = remember {
        mutableStateOf(0)
    }
    var selected = remember {
        mutableStateOf(productList.value.firstOrNull()?.name ?: "")
    }
    var isexpanded = remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(modifier = Modifier.padding(vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ExposedDropdownMenuBox(expanded = isexpanded.value , onExpandedChange = {
                isexpanded.value = ! isexpanded.value
            }) {
                TextField(value = selected.value, onValueChange = {}, readOnly = true, modifier = Modifier.menuAnchor(), trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isexpanded.value
                    )})
                ExposedDropdownMenu(
                    expanded = isexpanded.value,
                    onDismissRequest = { isexpanded.value = false }) {
                    productList.value.forEach {
                        DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                            selected.value = it.name
                            isexpanded.value = false
                            product_id.value = it.id
                        })
                    }
                }
            }
            val quantity = remember {
                mutableStateOf("")
            }

            OutlinedTextField(value =quantity.value , onValueChange = {
                quantity.value = it
            },modifier = Modifier
                .width(200.dp)
                .padding(5.dp), placeholder = { Text(text = "Quanity") })

            val context = LocalContext.current
            val scope = rememberCoroutineScope()

            Button(onClick = {
                viewmodel.setCartItem(product_id.value,selected.value,quantity.value.toInt())
                Toast.makeText(
                    context,
                    "${product_id.value} is added to cart}",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text(text="Add to Cart")
            }

        }
    }

}