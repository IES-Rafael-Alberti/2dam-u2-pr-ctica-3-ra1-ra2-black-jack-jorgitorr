package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

import android.content.Context
import com.jorgearceruiz97.black_jack_jorgitorr.R


/**
 * @property cartas contiene una lista de cartas
 */
class Baraja {
    companion object {
        private var cartas:ArrayList<Carta> = ArrayList()


        /**
         * crea una baraja de cartas
         */

        fun crearBaraja(context: Context){
            cartas.clear()//primero limpiamos las cartas

            var naipes = Naipes.values()

            for (i in 1 until 14){
                cartas.add(Carta(naipes[i], Palos.TREBOL, naipes[i].valorMax, naipes[i].valorMin, getIdDrawable(context,"${Palos.TREBOL.nombrePalo}${i}")))
                cartas.add(Carta(naipes[i], Palos.CORAZONES, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${Palos.CORAZONES.nombrePalo}${i}")))
                cartas.add(Carta(naipes[i], Palos.DIAMANTE, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${Palos.DIAMANTE.nombrePalo}${i}")))
                cartas.add(Carta(naipes[i], Palos.PICAS, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${Palos.PICAS.nombrePalo}${i}")))
            }

        }


        /**
         * Baraja las cartas
         */
        fun barajar(){
            cartas.shuffle()
        }

        /**
         * guarda la ultima carta de la baraja y la elimina de la lista
         * @return devuelve la carta con la ultima carta de la baraja
         */
        fun dameCarta(): Carta {
            val carta = cartas.last()
            cartas.removeLast()
            return carta
        }


        /**
         * devuelve la carta boca abajo
         * @return carta boca abajo
         */
        fun dameCartaBocaAbajo(): Carta {
            return Carta(Naipes.NINGUNA,Palos.NINGUNA,0,0,R.drawable.facedown)
        }

        /**
         * @return devuelve el tamanio de las cartas
         */
        fun tamanioBaraja():Int {
            return cartas.size
        }

        /**
         * consigue el idDrawable de la carta
         * @param context contexto
         * @param nombreCarta nombre de la carta
         */
        private fun getIdDrawable(context: Context, nombreCarta: String) =
            context.resources.getIdentifier(
                nombreCarta,
                "drawable",
                context.packageName)

    }
}