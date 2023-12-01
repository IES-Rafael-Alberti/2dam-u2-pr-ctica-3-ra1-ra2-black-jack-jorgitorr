package com.jorgearceruiz97.black_jack_jorgitorr.data

import androidx.annotation.DrawableRes
import com.jorgearceruiz97.black_jack_jorgitorr.clases.Naipes
import com.jorgearceruiz97.black_jack_jorgitorr.clases.Palos


data class Carta (val nombre: Naipes, val palo: Palos, val puntosMax:Int, val puntosMin: Int, @DrawableRes val idDrawable:Int){




}