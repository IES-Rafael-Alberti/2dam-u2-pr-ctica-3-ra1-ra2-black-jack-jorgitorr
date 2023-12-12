package com.jorgearceruiz97.black_jack_jorgitorr.clases

import com.jorgearceruiz97.black_jack_jorgitorr.R
import com.jorgearceruiz97.black_jack_jorgitorr.data.Carta


class Baraja {
    companion object {
        var cartas = ArrayList<Carta>()


        /**
         * crea una baraja de cartas
         */

        fun crearBaraja(){
            var naipes = Naipes.values()

            for (i in 1 until 13){
                cartas.add(Carta(naipes[i],Palos.TREBOL, naipes[i].valorMax, naipes[i].valorMin,i))
                cartas.add(Carta(naipes[i],Palos.CORAZONES, naipes[i].valorMax, naipes[i].valorMin,i))
                cartas.add(Carta(naipes[i],Palos.DIAMANTE, naipes[i].valorMax, naipes[i].valorMin,i))
                cartas.add(Carta(naipes[i],Palos.PICAS, naipes[i].valorMax, naipes[i].valorMin,i))
            }

        }



        fun barajar(){
            cartas.shuffle()
        }

        /**
         * guarda la ultima carta de la baraja y la elimina de la lista
         * @return devuelve la carta con la ultima carta de la baraja
         */
        fun dameCarta(): Carta {
            if(cartas.size!=0){
                var carta = cartas[cartas.size-1]
                cartas.remove(carta)
                return carta
            }else{
                return Carta(Naipes.CERO,Palos.CERO,0,0, R.drawable.facedown)
            }

        }

    }
}