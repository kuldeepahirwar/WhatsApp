package com.kuldeep.whatsapp.presentations.callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavouritesItem(contact: FavouriteContact) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
    ){
        Image(
            painter = painterResource(contact.image),
            contentDescription = null,
            modifier = Modifier.size(50.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text=contact.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}