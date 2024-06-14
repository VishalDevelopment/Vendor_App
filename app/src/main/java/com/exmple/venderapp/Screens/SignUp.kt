package com.exmple.venderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.exmple.venderapp.MainActivity
import com.exmple.venderapp.R
import com.exmple.venderapp.RetroFit.RetrofitInstance
import com.exmple.venderapp.Routes
import com.exmple.venderapp.ViewModels.SignupScreenViewModel
import com.exmple.venderapp.ViewModels.State
import com.exmple.venderapp.ViewModels.Vm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUp(viewModel: Vm, navController: NavHostController) {

    when (viewModel.state.value) {
        State.DEFAULT.name -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                        .padding(top = 15.dp)
                )

                var name = remember {
                    mutableStateOf("")
                }
                Column {
                    OutlinedTextField(
                        value = name.value, onValueChange = {
                            name.value = it
                        },
                        placeholder = {
                            Text(text = "Name")
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 25.dp)

                    )
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
                    var Phone = remember {
                        mutableStateOf("")
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    OutlinedTextField(
                        value = Phone.value, onValueChange = {
                            Phone.value = it
                        },
                        placeholder = {
                            Text(text = "Phone No")
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    var Address = remember {
                        mutableStateOf("")
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    OutlinedTextField(
                        value = Address.value, onValueChange = {
                            Address.value = it
                        },
                        placeholder = {
                            Text(text = "Address")
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    var Pincode = remember {
                        mutableStateOf("")
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    OutlinedTextField(
                        value = Pincode.value, onValueChange = {
                            Pincode.value = it
                        },
                        placeholder = {
                            Text(text = "PinCode")
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
                    val mcontext = LocalContext.current

                    var Retrofit = RetrofitInstance.api

                    Button(
                        onClick = {
                            viewModel.createUser(name.value,Password.value,Address.value,email.value , Phone.value , Pincode.value)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    ) {
                        Text(text = "Sign Up", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())

                    }

                    Text(text = "Already have account ?" , modifier = Modifier
                        .padding(vertical = 10.dp).fillMaxWidth()
                        .clickable {
                            // code to go back to login
                            navController.navigate(Routes.Login)
                        }, textAlign = TextAlign.Center)

                }


            }
        }

        State.LOADING.name -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        State.Failed.name->{
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                navController.navigate(Routes.Failed)
            }
        }
        State.SUCCESS.name ->{
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                navController.navigate(Routes.Success)
            }
        }
        }

}
