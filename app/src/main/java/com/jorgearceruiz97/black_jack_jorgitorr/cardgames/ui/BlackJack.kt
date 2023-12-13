package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Carta


/**
 *@param navController The navigation controller used for navigating to different screens
 * @param highestCardViewModel The viewModel responsible for managing the Highest Card game logic
 */
@Composable
fun BlackJackScreen(navController: NavHostController,
                    blackjackviewmodel: BlackJackViewModel){
    val imagenId: Int by blackjackviewmodel.imageId.observeAsState(initial = 0)
    val descImagen: String by blackjackviewmodel.imageDesc.observeAsState(initial = "")

    BackHandler {
        blackjackviewmodel.restart()
        navController.popBackStack()
    }


    blackJackLayout(
        blackjackviewmodel =  blackjackviewmodel,
        imagenId = imagenId,
        descImagen = descImagen)
}


/**
 * parte visual
 */
@Composable
fun blackJackLayout(blackjackviewmodel: BlackJackViewModel,
                    imagenId:Int,
                    descImagen:String){


    val turno: Boolean by blackjackviewmodel.turno.observeAsState(initial = true)

    //pedir cartas primer jugador
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top){

        Button(enabled = turno, onClick = { blackjackviewmodel.creaCartasJugador(2)}, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Pedir carta")
        }
        Button(enabled = turno, onClick = { blackjackviewmodel.pasar(2) }, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Plantarse")
        }
    }


    //cartas jugador 1
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){

        Text(text = "Jugador ${blackjackviewmodel.player2.value!!.playerId}")
        LazyRow {
            //recorre las cartas del jugador devolviendolas una a una a un meétodo que imprime cada carta
            //y crea un image para cada una de ellas
            items(blackjackviewmodel.getCardsJugador(2)){ carta ->
                creaDibujoCartasJugador(carta = carta)
            }
        }
        Text(text = "${blackjackviewmodel.sumaPuntos(2)}")
    }


    //cartas jugador 2
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = "Jugador ${blackjackviewmodel.player1.value!!.playerId}")
        LazyRow {
            //recorre las cartas del jugador devolviendolas una a una a un meétodo que imprime cada carta
            //y crea un image para cada una de ellas
            items(blackjackviewmodel.getCardsJugador(1)){ carta ->
                creaDibujoCartasJugador(carta = carta)
            }
        }

        Text(text = "${blackjackviewmodel.sumaPuntos(1)}")
    }

        //obtiene cartas o pasa del jugador que seamos
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom){

            Button(enabled = turno,onClick = { blackjackviewmodel.creaCartasJugador(1) }, colors = ButtonDefaults.buttonColors(Color.White)) {
                Text(text = "Pedir carta")
            }
            Button(enabled = turno, onClick = { blackjackviewmodel.pasar(1) }, colors = ButtonDefaults.buttonColors(Color.White)) {
                Text(text = "Plantarse")
            }
        }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun insertarUsuarios(
    blackjackviewmodel: BlackJackViewModel,
    playerId: Int,
    nombre:String
) {

    //insertar player 1
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Introduce nombre player 1")
        OutlinedTextField(value = nombre, onValueChange = {
            blackjackviewmodel.onNickNameChange(playerId,it)})
    }

    //insertar player2
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {
        Text(text = "Introduce nombre player 2")
        OutlinedTextField(value = nombre, onValueChange = {
            blackjackviewmodel.onNickNameChange(playerId,it)})
    }
    
}


/**
 * Te devuelve un nuevo composable de una imagen
 * que contiene la carta del juegador
 * @param carta
 */
@Composable
fun creaDibujoCartasJugador(carta: Carta) {
    Image(
        modifier = Modifier
            .height(150.dp)
            .padding(vertical = 10.dp),
        painter = painterResource(id = carta.idDrawable),
        contentDescription = "${carta.nombre} de ${carta.palo}"
    )
}




