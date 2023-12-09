package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

import androidx.annotation.DrawableRes


/**
 * @property nombre nombre del naipe
 * @property palo palo de la carta
 * @property puntosMax puntos maximos que puede tener la carta
 * @property puntosMin puntos mínimos que puede tener la carta
 * @property idDrawable ID del dibujo de la carta
 */
data class Carta (val nombre: Naipes, val palo: Palos, val puntosMax:Int, val puntosMin: Int, @DrawableRes val idDrawable:Int)