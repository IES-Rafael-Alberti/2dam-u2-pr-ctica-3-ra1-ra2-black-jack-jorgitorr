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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Carta


/**
 *@param navController The navigation controller used for navigating to different screens
 * @param highestCardViewModel The viewModel responsible for managing the Highest Card game logic
 */
@Composable
fun BlackJackMP(navController: NavHostController,
                    blackjackviewmodel: BlackJackViewModel){
    val imagenId: Int by blackjackviewmodel.imageId.observeAsState(initial = 0)
    val descImagen: String by blackjackviewmodel.imageDesc.observeAsState(initial = "")

    BackHandler {
        blackjackviewmodel.restart()
        navController.popBackStack()
    }


    blackLayoutMP(
        blackjackviewmodel =  blackjackviewmodel,
        imagenId = imagenId,
        descImagen = descImagen,
        BackHandler {
            blackjackviewmodel.restart()
            navController.popBackStack()
        })

}

@Composable
fun blackLayoutMP(
    blackjackviewmodel: BlackJackViewModel,
    imagenId:Int,
    descImagen:String,
    backHandler: Unit
){


    val turno: Boolean by blackjackviewmodel.turno.observeAsState(initial = true)
    val stopPlayer1: Boolean by blackjackviewmodel.stopPlayer1.observeAsState(true)
    val stopPlayer2: Boolean by blackjackviewmodel.stopPlayer2.observeAsState(true)


    //texto para diferenciar jugadores
    textoJugadoresMP(blackjackviewmodel = blackjackviewmodel)

    //cartas jugador 1
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        LazyRow {
            items(blackjackviewmodel.getCardsJugador(1)){ carta ->
                creaImagenCartasMP(carta = carta)
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
            items(blackjackviewmodel.getCardsJugador(2)){ carta ->
                creaImagenCartasMP(carta = carta)
            }
        }
        blackjackviewmodel.sumaPuntos(2)//suma Puntos jugador2
    }


    //esto me dice quien es el ganador que tengo que pasarlo a un metodo para que me lo muestre en otra pantalla
    var ganador = blackjackviewmodel.obtieneGanadorMP()
    //crea el dibujo de los ganadores
    Row(verticalAlignment = Alignment.Bottom,modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 150.dp, start = 50.dp)){
        if(blackjackviewmodel.condicionCrearGanadores()){
            creaDibujoGanadoresMP(playerId = ganador, blackjackviewmodel = blackjackviewmodel,backHandler)
        }
    }

    //imprime los botones
    botonesMP(blackjackviewmodel = blackjackviewmodel, stopPlayer2 = stopPlayer2, stopPlayer1 = stopPlayer1)

    //imprime los puntos de los jugadores
    puntosJugadoresMP(blackjackviewmodel = blackjackviewmodel)

}

@Composable
fun creaImagenCartasMP(carta: Carta) {
    Image(
        modifier = Modifier
            .height(150.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        painter = painterResource(id = carta.idDrawable),
        contentDescription = "${carta.nombre} de ${carta.palo}"
    )
}

@Composable
fun puntosJugadoresMP(blackjackviewmodel: BlackJackViewModel) {
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

@Composable
fun botonesMP(blackjackviewmodel: BlackJackViewModel, stopPlayer2: Boolean, stopPlayer1: Boolean) {
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top){

        Button(enabled = stopPlayer2, onClick = { blackjackviewmodel.creaCartasJugadorMP()}, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Pedir carta")
        }

        Button(enabled = stopPlayer2, onClick = { blackjackviewmodel.pasar(2) }, colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Plantarse")
        }
    }
}

@Composable
fun creaDibujoGanadoresMP(
    playerId: Int,
    blackjackviewmodel: BlackJackViewModel,
    backHandler: Unit
) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .padding(24.dp)
            .fillMaxWidth()){
        if(playerId == 0){
            Text(text = "Empate", color = Color.Black)
        }else{
            if(playerId==1){
                Text(text = "Has ganado", color = Color.Black)
            }else{
                Text(text = "Ha ganado el PC")
            }
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
@Composable
fun textoJugadoresMP(blackjackviewmodel: BlackJackViewModel) {
    //PC
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 420.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){

        Text(text = "PC")
    }

    //jugador
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 690.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom){

        Text(text = "Jugador")
    }
}
