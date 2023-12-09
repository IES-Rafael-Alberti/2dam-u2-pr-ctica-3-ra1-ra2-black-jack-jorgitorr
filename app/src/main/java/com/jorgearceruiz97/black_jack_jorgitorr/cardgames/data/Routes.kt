package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

sealed class Routes(val routes:String){
    object menuInicio: Routes("menu inicio")
    object jugadorVsPc: Routes("Dos jugadores")
    object dosJugadores: Routes("Un jugador")

    object highestCard : Routes("highestCard")

}
