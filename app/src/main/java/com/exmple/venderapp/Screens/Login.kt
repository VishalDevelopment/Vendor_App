package com.exmple.venderapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.exmple.venderapp.Routes
import com.exmple.venderapp.ViewModels.Vm

@Composable

fun Login(navController: NavHostController, viewModel: Vm) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        var email = remember {
            mutableStateOf("")
        }
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = email.value, onValueChange = {
                email.value = it
            },
            placeholder = {
                Text(text = "Email")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        var Password = remember {
            mutableStateOf("")
        }
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = Password.value,
            onValueChange = {
                Password.value = it
            },
            placeholder = {
                Text(text = "Password")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(25.dp))

//

        Button(
            onClick = {
                      viewModel.LoginUser(email.value,Password.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Text(text = "Sign Up")

        }

        Text(text = "New User ?" , modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable {
                // code to go back to login
                navController.navigate(Routes.Signup)
            })
    }

}