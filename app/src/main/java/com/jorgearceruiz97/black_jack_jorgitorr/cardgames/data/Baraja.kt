package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data

import android.content.Context
import com.jorgearceruiz97.black_jack_jorgitorr.R


class Baraja {
    companion object {
        private var cartas = ArrayList<Carta>()


        /**
         * crea una baraja de cartas
         */

        fun crearBaraja(context: Context){
            cartas.clear()

            var puntosMax: Int
            var puntosMin: Int

            for(palo in 1..4){
                for(cont in 1..13){
                    when(cont){
                        1->{
                            puntosMin = 1
                            puntosMax = 11
                        }
                        11, 12, 13 ->{
                            puntosMin = 10
                            puntosMax = 10
                        }
                        else -> {
                            puntosMin = cont
                            puntosMax = cont
                        }
                    }

                    cartas.add(
                        Carta(Naipes.values()[cont],
                            Palos.values()[palo],
                            puntosMin,
                            puntosMax,
                            getIdDrawable(context,
                                "${Naipes.values()[palo]}_${cont}")

                    ))
                }
            }
            /*
            for (i in 1 until 14){
                cartas.add(Carta(naipes[i], Palos.TREBOL, naipes[i].valorMax, naipes[i].valorMin,
                    getIdDrawable(context,"${naipes[i]}")
                ))
                cartas.add(
                    Carta(naipes[i],
                    Palos.CORAZONES, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${naipes[i]}"))
                )
                cartas.add(Carta(naipes[i], Palos.DIAMANTE, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${naipes[i]}")))
                cartas.add(Carta(naipes[i], Palos.PICAS, naipes[i].valorMax, naipes[i].valorMin,getIdDrawable(context,"${naipes[i]}")))
            }*/

        }



        fun barajar(){
            cartas.shuffle()
        }

        /**
         * guarda la ultima carta de la baraja y la elimina de la lista
         * @return devuelve la carta con la ultima carta de la baraja
         */
        fun dameCarta(): Carta {
            var carta = cartas.last()
            cartas.removeLast()
            return carta
        }


        /**
         * devuelve la carta boca abajo
         */
        fun dameCartaBocaAbajo(): Carta {
            return Carta(Naipes.CERO,Palos.CERO,0,0,R.drawable.facedown)
        }

        fun tamanioBaraja():Int {
            return cartas.size
        }

        private fun getIdDrawable(context: Context, nombreCarta: String) =
            context.resources.getIdentifier(
                nombreCarta,
                "drawable",
                context.packageName)

    }
}