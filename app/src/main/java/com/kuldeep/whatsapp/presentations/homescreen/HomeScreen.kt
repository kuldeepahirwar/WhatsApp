package com.kuldeep.whatsapp.presentations.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kuldeep.whatsapp.R
import com.kuldeep.whatsapp.presentations.bottomnavigation.BottomNavigation

@Preview(showSystemUi = true)
@Composable
fun HomeScreen() {

    val chatData = listOf(
        ChatDesignModel(R.drawable.boy, "Salman Khan", "10:30 AM", "Hey, how are you?"),
        ChatDesignModel(R.drawable.boy1, "Anita", "10:30 AM", "Hey, how are you?"),
        ChatDesignModel(R.drawable.boy, "Vandna", "10:30 AM", "Hey, how are you?"),
        ChatDesignModel(R.drawable.boy, "Suresh", "10:30 AM", "Hey, how are you?"),

        )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { },containerColor = colorResource(R.color.teal_200),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White) {
                Icon(
                    painter = painterResource(R.drawable.chat_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigation()
        }
    ) {
       Column(modifier = Modifier.padding(it)) {
           Spacer(modifier = Modifier.height(16.dp))
           Box(modifier = Modifier.fillMaxWidth()){
               Text(
                   text = "WhatsApp",
                   modifier = Modifier.padding(start = 16.dp, top = 8.dp).align(Alignment.CenterStart),
                   color = colorResource(R.color.teal_200),
                   fontSize = 24.sp
               )
               Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                   IconButton(onClick = {}) {
                          Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                          )

                   }
                   IconButton(onClick = {}) {
                       Icon(
                           painter = painterResource(id = R.drawable.search),
                           contentDescription = null,
                           modifier = Modifier.size(24.dp)
                       )

                   }
                   IconButton(onClick = {}) {
                       Icon(
                           painter = painterResource(id = R.drawable.more),
                           contentDescription = null,
                           modifier = Modifier.size(24.dp)
                       )

                   }
               }
           }
           HorizontalDivider()
           LazyColumn{
             items(chatData.size){
                 ChatDesign(chatDesignModel = chatData[it])
             }
           }
       }
    }
}