package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun BlackJackScreen(navController: NavHostController,
                    blackjackviewmodel: BlackJackViewModel){
    val imagenId: Int by blackjackviewmodel.imageId.observeAsState(initial = 0)
    val descImagen: String by blackjackviewmodel.imageDesc.observeAsState(initial = "")


    val imageModifier = Modifier
        .size(900.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    BackHandler {
        blackjackviewmodel.restart()
        navController.popBackStack()
    }
    
    //Layout del usuario
    insertarUsuarios(blackjackviewmodel = blackjackviewmodel,
        introduceNombre = {blackjackviewmodel.introduceUsuario(it)})

    //Layout del blackJack
    blackJackLayout(blackjackviewmodel = blackjackviewmodel, imagenId = imagenId, descImagen = descImagen) {
        blackjackviewmodel.pasar()
    }
}


/**
 * parte visual
 */
@Composable
fun blackJackLayout(blackjackviewmodel: BlackJackViewModel,
                    imagenId:Int,
                    descImagen:String,
                    pasar:()->Unit){

    val turno: Boolean by blackjackviewmodel.turno.observeAsState(initial = true)
    val player1Id = blackjackviewmodel.player1.value!!.playerId
    val player2Id = blackjackviewmodel.player2.value!!.playerId

    //cartas jugador 1
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){

        Text(text = "Jugador 1")
        LazyColumn {
            items(blackjackviewmodel.getCardsJugador(player1Id).size){
                Image(painter = painterResource(id = imagenId),
                    contentDescription = descImagen)
            }
        }
    }
    Text(text = "${blackjackviewmodel.sumaPuntos(player1Id)}")

    //cartas jugador 2
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 250.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = "Jugador 2")
        LazyColumn {
            items(blackjackviewmodel.getCardsJugador(player2Id).size){
                Image(painter = painterResource(id = R.drawable.facedown),
                    contentDescription = "")
            }
        }
    }
    Text(text = "${blackjackviewmodel.sumaPuntos(player2Id)}")

    //pedir Carta
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){
        Button(onClick = { blackjackviewmodel.getCarta() }, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
            Text(text = "Pedir carta")
        }
        Button(onClick = { pasar() }, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
            Text(text = "Plantarse")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun insertarUsuarios(
    blackjackviewmodel: BlackJackViewModel,
    introduceNombre: (String) -> Unit,
) {
    val nombre: String by blackjackviewmodel.player1Name.observeAsState("")
    val nombre2: String by blackjackviewmodel.player2Name.observeAsState("")

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Introduce nombre player 1")
        OutlinedTextField(value = nombre, onValueChange = {introduceNombre})
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Introduce nombre player 2")
        OutlinedTextField(value = nombre2, onValueChange = { introduceNombre})
    }
    
}



