package com.kuldeep.whatsapp.presentations.callscreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuldeep.whatsapp.R

@Composable
@Preview(showSystemUi = true)
fun FavouriteSection(){
    val sampleFavourites = listOf(
        FavouriteContact(R.drawable.bhuvan_bam, "Kuldeep"),
        FavouriteContact(R.drawable.boy1, "John Doe"),
        FavouriteContact(R.drawable.disha_patani, "Jane Smith") ,
        FavouriteContact(R.drawable.bhuvan_bam, "Kuldeep"),
        FavouriteContact(R.drawable.boy1, "John Doe"),
        FavouriteContact(R.drawable.disha_patani, "Jane Smith"),
        FavouriteContact(R.drawable.bhuvan_bam, "Kuldeep"),
        FavouriteContact(R.drawable.boy1, "John Doe"),
        FavouriteContact(R.drawable.disha_patani, "Jane Smith")
    )
    Column (
        modifier = Modifier.padding(start=10.dp, bottom = 8.dp)
    ){

        Text(
            text="Favourites",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row (modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())){

            sampleFavourites.forEach{
                FavouritesItem(it)
            }
        }
    }
}

data class FavouriteContact(
    val image:Int,
    val name:String,
)