package com.kuldeep.whatsapp.presentations.updatescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kuldeep.whatsapp.R
import com.kuldeep.whatsapp.presentations.bottomnavigation.BottomNavigation

@Composable
@Preview
fun UpdateScreen() {

    val scrollState = rememberScrollState()
    val sampleStatus = listOf(
        StatusData(
            image = R.drawable.bhuvan_bam,
            name = "Bhuvan Bam",
            time = "10:00 AM"
        ),
        StatusData(
            image = R.drawable.disha_patani,
            name = "Disha Bam",
            time = "10 Minutes Ago"
        ),
        StatusData(
            image = R.drawable.boy,
            name = "Sachin Tendulkar",
            time = "1 Hour Ago"
        ),
    )

    val sampleChannel = listOf(
        Channel(
            image = R.drawable.boy1,
            name = "Kuldeep rock",
            description = "Get latest news"
        ),
        Channel(
            image = R.drawable.boy3,
            name = "Rahul",
            description = "Get latest news"
        )
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}, containerColor = colorResource(R.color.teal_200),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigation()
        },
        topBar = {
            TopBar()
        }
    ) {
        Column (modifier = Modifier.padding(it)
            .fillMaxSize()
            .verticalScroll(scrollState)){
             Text(
                text="Status",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color =Color.Black,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            MyStatus()
            sampleStatus.forEach { status ->
                StatusItem(status = status)
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color.LightGray)
            Text(
                text = "Channels",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(
                    text ="Stay updated on topics that matter to you.Find channels to follow below.",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Find Channels to follow",
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            sampleChannel.forEach{channel ->
                ChannelItemDesign(channel)

        }

        }
    }
}