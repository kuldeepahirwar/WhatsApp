package com.kuldeep.whatsapp.presentations.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kuldeep.whatsapp.R
import com.kuldeep.whatsapp.presentations.navigation.Routes

@Composable
fun WelcomeScreen(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id= R.drawable.whatsapp_sticker),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Text(
            text =" Welcome to WhatsApp",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row{

            Text(
                text = buildAnnotatedString {
                    withStyle(style= SpanStyle(color=Color.Gray)){
                        append("Read our ")
                    }
                    withStyle(style= SpanStyle(color = Color.Green)) {
                        append("Privacy Policy")
                    }
                    withStyle(style= SpanStyle(color= Color.Gray)) {
                        append(". Tap 'Agree and continue' to")
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row{
            Text(text = "accept the", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = " Terms of Service",
                color = Color.Green,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Button(
            modifier = Modifier.size(280.dp, 48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(R.color.teal_200)),
            onClick = {navHostController.navigate(Routes.UserRegistrationScreen) }
        ) {
            Text(text = "Agree and continue",)
        }

    }
}
