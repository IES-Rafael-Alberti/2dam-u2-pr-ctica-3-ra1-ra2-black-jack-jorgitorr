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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jorgearceruiz97.black_jack_jorgitorr.R


/**
 *@param navController The navigation controller used for navigating to different screens
 * @param highestCardViewModel The viewModel responsible for managing the Highest Card game logic
 */
@Composable
fun BlackJackScreen(
    navController: NavHostController,
    blackjackviewmodel: BlackJackViewModel
){
    val imagenId: Int by blackjackviewmodel.imageId.observeAsState(initial = 0)
    val descImagen: String by blackjackviewmodel.imageDesc.observeAsState(initial = "")

    val imageModifier = Modifier
        .size(900.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    //cartas 1 jugador
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){
        Image(painter = painterResource(id = R.drawable.facedown),
            contentDescription = "")
    }

    //cartas 2 jugador
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 250.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id = R.drawable.facedown),
            contentDescription = "")
    }

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){
        Button(onClick = {  }, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
            Text(text = "Pedir carta")
        }
        Button(onClick = {  }, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
            Text(text = "Plantarse")
        }
    }


}


@Composable
fun insertarUsuario1(navController: NavHostController, blackjackviewmodel: BlackJackViewModel){
    val nombre: String by blackjackviewmodel.player1Name.observeAsState("")

    Box(){
        Text(text = "Introduce tu nombre")

    }

}



