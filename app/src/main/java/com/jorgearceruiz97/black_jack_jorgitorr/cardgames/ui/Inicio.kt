package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jorgearceruiz97.black_jack_jorgitorr.R
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Routes


/**
 * contiene el menu principal que te lleva a los diferentes juegos
 */
@Composable
fun menuInicio(navController: NavHostController){
    val imageModifier = Modifier
        .size(900.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    Image(painter = painterResource(id = R.drawable.fondo), contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = imageModifier)
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){
    }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 50.dp)){
            Button(onClick = { navController.navigate(Routes.blackJack2Players.routes)}) {
                Text(text = "BJ 2 Players")
            }
            Button(onClick = { navController.navigate(Routes.highestCard.routes)}) {
                Text(text = "Highest Card")

        }
    }
    
}

