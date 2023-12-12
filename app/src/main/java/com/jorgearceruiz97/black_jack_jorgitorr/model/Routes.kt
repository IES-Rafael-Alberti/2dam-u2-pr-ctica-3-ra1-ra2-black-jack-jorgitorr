package com.jorgearceruiz97.black_jack_jorgitorr.model

sealed class Routes(val routes:String){
    object menuInicio: Routes("menu inicio")
    object dosJugadores: Routes("Dos jugadores")
    object unJugador: Routes("Un jugador")

}
