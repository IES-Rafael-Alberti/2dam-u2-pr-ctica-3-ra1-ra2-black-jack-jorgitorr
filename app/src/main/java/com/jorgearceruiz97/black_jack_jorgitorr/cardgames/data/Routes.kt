package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

sealed class Routes(val routes:String){
    object menuInicio: Routes("menu inicio")
    object blackJack1Player: Routes("Dos jugadores")
    object blackJack2Players: Routes("Un jugador")
    object highestCard : Routes("highestCard")
}
