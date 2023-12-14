package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data


/**
 * @property nombre nombre del jugador
 * @property listaCartas cartas del jugador
 * @property fichas fichas del jugador
 * @property puntos puntos del jugador
 */
data class Player(var playerId:Int, val nombre:String, val listaCartas: ArrayList<Carta>, val fichas:Int, var puntos:Int)
