package com.kuldeep.whatsapp.presentations.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kuldeep.whatsapp.presentations.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navHostController.navigate(Routes.WelcomeScreen){
            popUpTo(Routes.SplashScreen) {
                inclusive = true
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = androidx.compose.ui.res.painterResource(id = com.kuldeep.whatsapp.R.drawable.whatsapp_icon),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp).align(Alignment.Center)
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text="From", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = com.kuldeep.whatsapp.R.drawable.meta),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = colorResource(id = com.kuldeep.whatsapp.R.color.teal_200)
                )
                Text(text = "Meta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}