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
        _player1.value!!.playerId = 1
        _player2.value!!.playerId = 2
    }


    fun getCarta(){
        _card.value = Baraja.dameCarta()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
    }


    fun getCardsJugador1(): ArrayList<Carta> {
        return player1.value!!.listaCartas
    }

    fun onNickNameChange(playerId:Int, nombre:String){
        if(playerId==_player1.value!!.playerId){
            _player1Name.value = nombre
        }else if(playerId==_player2.value!!.playerId){
            _player2Name.value = nombre
        }

        //si el jugador 1 y 2 no tienen valor vacio pueden darle a aceptar
        _showBtnAccept.value = _player1Name.value!!.isNotEmpty() && _player2Name.value!!.isNotEmpty()
    }

    fun pasar(playerId:Int){
        if(playerId == _player1.value!!.playerId){
            _stopPlayer1.value = true
        }else if(playerId == _player2.value!!.playerId){
            _stopPlayer2.value = true
        }
    }


    fun cambiaTurnos(){
        _turno.value = _turno.value != true
    }


    /**
     * comienza un nuevo juego
     */
    fun nuevoJuego(){
        restart()
    }

    fun obtieneGanador():Int{
        var sumaPlayer1 = 0
        var sumaPlayer2 = 0
        //suma cartas player1
        for(carta in _player1.value!!.listaCartas){
            sumaPlayer1 += if(sumaPlayer1<23){//tengo que ver reglas blackjack
                carta.puntosMax
            }else{
                carta.puntosMin
            }
        }
        //suma cartas Player2
        for(carta in _player2.value!!.listaCartas){
            sumaPlayer2 += if(sumaPlayer2<23){
                carta.puntosMax
            }else{
                carta.puntosMin
            }
        }

        //devuelve el que tiene más puntos de los dos
        return if(sumaPlayer1>sumaPlayer2){
            player1.value!!.playerId
        }else{
            player2.value!!.playerId
        }
    }

    fun obtieneCartas(playerId: Int){
        if(playerId == _player1.value!!.playerId){

        }
    }













}