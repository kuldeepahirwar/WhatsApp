package com.kuldeep.whatsapp.presentations.callscreen

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuldeep.whatsapp.R

@Composable
fun CallItemDesign(call: Call) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Image(
            painter = painterResource(call.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = call.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Row {
                Icon(
                    painter = painterResource(R.drawable.baseline_call_missed_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if(call.isMissed){
                        androidx.compose.ui.graphics.Color.Red
                    } else {
                        androidx.compose.ui.graphics.Color.Green
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text="Yesterday, 10:30 PM", fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.Gray)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    painter = painterResource(R.drawable.telephone),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}