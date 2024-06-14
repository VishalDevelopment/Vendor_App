package com.exmple.venderapp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exmple.venderapp.Screens.Cart
import com.exmple.venderapp.Screens.Failed
import com.exmple.venderapp.Screens.Home
import com.exmple.venderapp.Screens.Login
import com.exmple.venderapp.Screens.Order
import com.exmple.venderapp.Screens.Profile
import com.exmple.venderapp.Screens.SignUp
import com.exmple.venderapp.Screens.Success
import com.exmple.venderapp.ViewModels.Vm

@Composable
fun NavGraph(viewModel: Vm) {
    val navController = rememberNavController()

    val user_id = viewModel.getid().collectAsState(initial = "")

    if (user_id.value==""){
        Log.d("user_id",user_id.value)
        NavHost(navController = navController, startDestination = Routes.Login){
            composable<Routes.Signup>{
                SignUp(viewModel = viewModel,navController)
            }
            composable<Routes.Failed> {
                Failed(navController)
            }
            composable<Routes.Login> {
                Login(navController,viewModel)
            }
            composable<Routes.Success> {
                Success(navController)
            }
        }
    }
    else{
        Log.d("user_id",user_id.value)
            val productList = viewModel.productList.collectAsState()
            Log.d("VVm","${productList.value}")
            val navController = rememberNavController()
            val selectedIcon = remember {
                mutableStateOf(Icons.Default.Home)
            }

            Scaffold(
                bottomBar = {
                    BottomAppBar(containerColor = MaterialTheme.colorScheme.primary) {

                        IconButton(onClick = {
                            selectedIcon.value = Icons.Default.Home
                            navController.navigate(MainActivity.BottomNav.Home.route) {
                                popUpTo(0)
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Default.Home, contentDescription = null)
                        }

                        IconButton(onClick = {
                            selectedIcon.value = Icons.Default.Create
                            navController.navigate(MainActivity.BottomNav.Order.route) {
                                popUpTo(MainActivity.BottomNav.Home.route)
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = null)

                        }
                        IconButton(onClick = {
                            selectedIcon.value = Icons.Default.ShoppingCart
                            navController.navigate(MainActivity.BottomNav.Cart.route) {
                                popUpTo(MainActivity.BottomNav.Home.route)
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null)
                        }
                        IconButton(onClick = {
                            selectedIcon.value = Icons.Default.AccountCircle
                            navController.navigate(MainActivity.BottomNav.Profile.route) {
                                popUpTo(MainActivity.BottomNav.Home.route)
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null)
                        }
                    }
                }
            ) { paddingValues ->


                Box(modifier = Modifier.padding(paddingValues)) {
                    NavHost(navController = navController, startDestination = MainActivity.BottomNav.Home.route) {
                        composable(MainActivity.BottomNav.Home.route) {
                            Home()
                        }
                        composable(MainActivity.BottomNav.Order.route) {
                            Order(viewModel)
                        }
                        composable(MainActivity.BottomNav.Cart.route) {
                            Cart(viewModel)
                        }
                        composable(MainActivity.BottomNav.Profile.route) {
                            Profile()
                        }
                    }
                }
            }
        }

    }

