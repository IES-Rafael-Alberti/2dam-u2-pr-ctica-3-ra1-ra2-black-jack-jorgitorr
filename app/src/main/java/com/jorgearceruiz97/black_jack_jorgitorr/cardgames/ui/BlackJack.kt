package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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


    blackLayout(
        blackjackviewmodel =  blackjackviewmodel,
        imagenId = imagenId,
        descImagen = descImagen,
        BackHandler {
            blackjackviewmodel.restart()
            navController.popBackStack()
        })

}


/**
 * parte visual
 */
@Composable
fun blackLayout(
    blackjackviewmodel: BlackJackViewModel,
    imagenId:Int,
    descImagen:String,
    backHandler: Unit
){
    val stopPlayer1: Boolean by blackjackviewmodel.stopPlayer1.observeAsState(true)
    val stopPlayer2: Boolean by blackjackviewmodel.stopPlayer2.observeAsState(true)


    //texto para diferenciar jugadores
    textoJugadores(blackjackviewmodel = blackjackviewmodel)

    //cartas jugador 1
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        LazyRow {
            //recorre las cartas del jugador devolviendolas una a una a un meétodo que imprime cada carta
            //y crea un image para cada una de ellas
            items(blackjackviewmodel.getCardsJugador(1)){ carta ->
                creaImagenCartas(carta = carta)
            }
        }
        blackjackviewmodel.sumaPuntos(1)//suma Puntos jugador1

    }

    //cartas jugador 2
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){

        LazyRow {
            //recorre las cartas del jugador devolviendolas una a una a un meétodo que imprime cada carta
            //y crea un image para cada una de ellas
            items(blackjackviewmodel.getCardsJugador(2)){ carta ->
                creaImagenCartas(carta = carta)
            }
        }
        blackjackviewmodel.sumaPuntos(2)//suma Puntos jugador2
    }


    //esto me dice quien es el ganador que tengo que pasarlo a un metodo para que me lo muestre en otra pantalla
    var ganador = blackjackviewmodel.obtieneGanador()
    //crea el dibujo de los ganadores
    Row(verticalAlignment = Alignment.Bottom,modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 150.dp)){
        if(blackjackviewmodel.condicionCrearGanadores()){
            creaDibujoGanadores(playerId = ganador, blackjackviewmodel = blackjackviewmodel,backHandler)
        }
    }

    //imprime los botones
    botones(blackjackviewmodel = blackjackviewmodel, stopPlayer2 = stopPlayer2, stopPlayer1 = stopPlayer1)

    //imprime los puntos de los jugadores
    puntosJugadores(blackjackviewmodel = blackjackviewmodel)

}


/**
 * Primera pantalla en la que se introducen los nombres de los jugadores
 * y aquí también quiero poner lo que van a apostar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun insertarUsuarios(
    blackjackviewmodel: BlackJackViewModel,
    playerId: Int,
) {

    //insertar player 1
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Introduce nombre player 1")
        OutlinedTextField(value = blackjackviewmodel.player1Name.value!!, onValueChange = {
            blackjackviewmodel.onNickNameChange(playerId,it)})
    }

    //insertar player2
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {
        Text(text = "Introduce nombre player 2")
        OutlinedTextField(value = blackjackviewmodel.player1Name.value!!, onValueChange = {
            blackjackviewmodel.onNickNameChange(playerId,it)})
    }
    
}


/**
 * Te devuelve un nuevo composable de una imagen con una carta
 * que contiene la carta del juegador
 * @param carta le pasa la imagen de la carta que quiero imprimir
 */
@Composable
private fun creaImagenCartas(carta: Carta) {
    Image(
        modifier = Modifier
            .height(150.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        painter = painterResource(id = carta.idDrawable),
        contentDescription = "${carta.nombre} de ${carta.palo}"
    )
}


/**
 * Crea un dibujo con el ganador
 * @param playerId le pasa el id del ganador
 * @param blackjackviewmodel
 */
@Composable
private fun creaDibujoGanadores(playerId: Int, blackjackviewmodel: BlackJackViewModel,
                        BackHandler:Unit){
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .padding(24.dp)
            .fillMaxWidth()){
        if(playerId == 0){
            Text(text = "Empate", color = Color.Black)
        }else{
            Text(text = "El ganador es $playerId", color = Color.Black)
        }

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()){
            Button(modifier = Modifier.padding(5.dp),onClick = { blackjackviewmodel.restart() }) {
                Text(text = "Reiniciar")
            }

        }

    }

}


/**
 * Muestra los puntos obtenidos por los jugadores
 * @param blackjackviewmodel
 */
@Composable
private fun puntosJugadores(blackjackviewmodel: BlackJackViewModel){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 500.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){
        Text(text = "${blackjackviewmodel.player2.value!!.puntos} puntos")
    }

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 200.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){
        Text(text = "${blackjackviewmodel.player1.value!!.puntos} puntos")
    }
}

/**
 * muestra los botones
 * @param blackjackviewmodel
 * @param stopPlayer1 que es un booleano para saber si el jugador1 está parado
 * @param stopPlayer2 que es un booleano para saber si el jugador2 está parado
 */
@Composable
private fun botones(blackjackviewmodel: BlackJackViewModel, stopPlayer2: Boolean, stopPlayer1:Boolean){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top){

        Button(enabled = stopPlayer2, onClick = { blackjackviewmodel.creaCartasJugador(2)}, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Pedir carta")
        }

        Button(enabled = stopPlayer2, onClick = { blackjackviewmodel.pasar(2) }, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Plantarse")
        }
    }

    //quiero cambiar la rotacion de estos botones para ponerlos en la dirección del otro jugador
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){

        Button(enabled = stopPlayer1,onClick = { blackjackviewmodel.creaCartasJugador(1) }, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Pedir carta")
        }

        Button(enabled = stopPlayer1, onClick = { blackjackviewmodel.pasar(1) }, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Plantarse")
        }
        //suma puntos del jugador
    }

}

/**
 * Texto para diferenciar jugadores
 * @param blackjackviewmodel
 */
@Composable
private fun textoJugadores(blackjackviewmodel: BlackJackViewModel){
    //jugador 1
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 420.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){

        Text(text = "Jugador ${blackjackviewmodel.player1.value!!.playerId}")
    }

    //jugador 2
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 690.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){

        Text(text = "Jugador ${blackjackviewmodel.player2.value!!.playerId}")
    }

}







