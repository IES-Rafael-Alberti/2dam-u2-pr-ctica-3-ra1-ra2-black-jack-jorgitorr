package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

/**
 * @property nombrePalo nombre del palo para poder cogerlo de idDrawable que es la letra y el número
 */
enum class Palos(val nombrePalo:String){
    NINGUNA("CERO"),
    CORAZONES("c"),
    TREBOL("t"),
    DIAMANTE("d"),
    PICAS("p");
}