package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

/**
 * Rutas del proyecto
 * @param routes rutas del proyecto
 */
sealed class Routes(val routes:String){
    object menuInicio: Routes("menu inicio")
    object blackJack2Players: Routes("2 jugadores ")
    object BlackJackMP: Routes("Jugador vs Maquina")
    object highestCard : Routes("highestCard")
}
