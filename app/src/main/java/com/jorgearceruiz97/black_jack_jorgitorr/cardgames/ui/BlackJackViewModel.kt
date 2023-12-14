package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Baraja
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Carta
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Player

/**
 * logica del programa
 * @property context
 * @property _imageId
 */
class BlackJackViewModel(application:Application):AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc: LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Carta>()
    val card: LiveData<Carta> = _card

    private val _player1 = MutableLiveData<Player>()
    val player1: LiveData<Player> = _player1
    private val _player2 = MutableLiveData<Player>()
    val player2: LiveData<Player> = _player2

    private val _player1Name = MutableLiveData<String>()
    val player1Name : LiveData<String> = _player1Name
    private val _player2Name = MutableLiveData<String>()
    val player2Name : LiveData<String> = _player2Name

    private var _stopPlayer1 = MutableLiveData<Boolean>()
    var stopPlayer1 : LiveData<Boolean> = _stopPlayer1

    private var _stopPlayer2 = MutableLiveData<Boolean>()
    var stopPlayer2 : LiveData<Boolean> = _stopPlayer2

    private val _turno = MutableLiveData<Boolean>()
    val turno: LiveData<Boolean> = _turno


    private var _showBtnAccept = MutableLiveData<Boolean>()
    val showBtnAccept:LiveData<Boolean> = _showBtnAccept


    init {
        restart()
    }

    fun restart(){
        Baraja.crearBaraja(context)
        Baraja.barajar()
        nuevoJuego()
    }


    /**
     * devuelve una carta de la baraja y te la da en forma de objeto
     */
    fun getCarta():Carta{
        _card.value = Baraja.dameCarta()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
        return _card.value!!
    }



    fun getCardsJugador(playerId: Int): ArrayList<Carta> {
        return if(playerId==1){
            player1.value!!.listaCartas
        }else{
            player2.value!!.listaCartas
        }
    }



    fun onNickNameChange(playerId:Int, nombre:String){
        if(playerId==1){
            _player1Name.value = nombre
        }else {
            _player2Name.value = nombre
        }

        //si el jugador 1 y 2 no tienen valor vacio pueden darle a aceptar
        //_showBtnAccept.value = _player1Name.value!!.isNotEmpty() && _player2Name.value!!.isNotEmpty()
    }


    /**
     * Cuando le das click a pasar la variable se actualiza a false
     * también se actualiza la pedirCarta del mismo jugador
     * Para desactivarte botones y que el jugador no pueda seguir sacando cartas
     */
    fun pasar(playerId: Int){
        if(playerId == 1){
            _stopPlayer1.value = false
        }else if(playerId == 2){
            _stopPlayer2.value = false
        }
    }





    /**
     * cambia de turno
     */
    fun cambiaTurnos(){
        _turno.value = _turno.value != true
    }




    /**
     * devuelve el id del jugador actual segun el turno en el que estemos
     */
    private fun getJugadorId():Int{
        var playerId: Int = 0
        playerId = if(_turno.value == true){
            _player1.value!!.playerId
        }else{
            _player2.value!!.playerId
        }
        return playerId
    }




    /**
     * comienza un nuevo juego
     */


    /**
     * suma los puntos del jugador
     * sacandole la última carta y sumandola
     * se reinicializan los puntos cada vez que se realiza este método para poner a cero los puntos
     */
    fun sumaPuntos(playerId: Int){
        _player1.value!!.puntos = 0
        _player2.value!!.puntos = 0

        if(playerId==_player1.value!!.playerId){
            //suma cartas player1
            for(carta in _player1.value!!.listaCartas){//aqui esta sumando todas las cartas, debería sumar la actual
                if(_player1.value!!.puntos+carta.puntosMax<=21){
                    _player1.value!!.puntos += carta.puntosMax
                }else{
                    _player1.value!!.puntos += carta.puntosMin
                }
            }
        }else{
            //suma cartas Player2
            for(carta in _player2.value!!.listaCartas){
                if(_player2.value!!.puntos+carta.puntosMax<=21){
                    _player2.value!!.puntos += carta.puntosMax
                }else{
                    _player2.value!!.puntos += carta.puntosMin
                }
            }
        }
    }

    /**
     * Obtiene el ganador del juego
     * @return si el jugador1 tiene mas de 21 puntos gana el jugador2
     * @return si el jugador2 tiene mas de 21 puntos gana el jugador1
     * @return sino se comprueba quien ha ganado
     */
    fun obtieneGanador():Int{
        //si la partida ha terminado: que es cuando uno de los dos alcanza o supera los 21 puntos
        if(_player1.value!!.puntos>=21){
            pasar(_player1.value!!.playerId)//desactiva al jugador1
        }else if(_player2.value!!.puntos>=21){
            pasar(_player2.value!!.playerId)//desactiva al jugador2
        }else if(!_stopPlayer1.value!! && !_stopPlayer2.value!!){
            if(_player1.value!!.puntos<=21 && _player1.value!!.puntos>_player2.value!!.puntos){
                return _player1.value!!.playerId
            }else if(_player1.value!!.puntos==_player2.value!!.puntos){
                return 0 //empate
            }else if(_player2.value!!.puntos<=21 && _player1.value!!.puntos>_player1.value!!.puntos){
                return _player2.value!!.playerId
            }
        }else{
            if(!stopPlayer1.value!! && _player2.value!!.puntos <=21){
                return _player2.value!!.playerId
            }else if(!stopPlayer2.value!! && _player1.value!!.puntos <= 21){
                return _player1.value!!.playerId
            }
        }
        return 0 //empate
    }

    /**
     * inicia un nuevo juego que consiste en crear el jugador con valores predeterminados
     * y crea al menos una carta en cada jugador para poder imprimir la carta boca abajo
     * reinicializa las variables de los botones
     */
    fun nuevoJuego(){
        _player1.value = Player(1,"",ArrayList(),50,0)
        _player2.value = Player(2,"",ArrayList(),50,0)
        creaCartasJugador(1)
        creaCartasJugador(2)
        _stopPlayer1.value = true
        _stopPlayer2.value = true
    }

    fun creaCartasJugador(playerId: Int){
        if(playerId==1){
            _player1.value!!.listaCartas.add(getCarta())
        }else{
            _player2.value!!.listaCartas.add(getCarta())
        }
    }

    /**
     * Condicion para cuando se debe crear el texto de los ganadores
     */
    fun condicionCrearGanadores():Boolean{
        return (!_stopPlayer1.value!! && !_stopPlayer2.value!!) || (_player1.value!!.puntos > 21
                || _player2.value!!.puntos > 21)
                || (_player1.value!!.puntos == 21 && _player2.value!!.puntos == 21)
                && (!_stopPlayer1.value!! && _player2.value!!.puntos <= 21)
                && (!_stopPlayer2.value!! && _player1.value!!.puntos <= 21)
    }


}