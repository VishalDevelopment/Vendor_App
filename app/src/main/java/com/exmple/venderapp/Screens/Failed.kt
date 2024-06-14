package com.exmple.venderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exmple.venderapp.R
import com.exmple.venderapp.Routes

@Composable
 fun Failed(navController: NavController){
   Box(Modifier.fillMaxSize()) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    Color.White
                )
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.failedlogo),
                    contentDescription = null
                )
                Text(
                    text = "Registeration Failed",
                    fontSize = 32.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(Routes.Signup)
                    }, colors = ButtonDefaults.buttonColors(Color.Black), modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Try Again ")
                }

            }

        }
    }
}