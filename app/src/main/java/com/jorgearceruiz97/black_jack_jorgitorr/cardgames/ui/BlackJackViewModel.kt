package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Carta
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Player

class BlackJackViewModel(application:Application):AndroidViewModel(application) {
    /**
     * logica del programa
     */
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc: LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Carta>()


    private val _player1 = MutableLiveData<Player>()
    val player1: LiveData<Player> = _player1
    private val _player2 = MutableLiveData<Player>()
    val player2: LiveData<Player> = _player2

    private val _player1Name = MutableLiveData<String>()
    val player1Name : LiveData<String> = _player1Name
    private val _player2Name = MutableLiveData<String>()
    val player2Name : LiveData<String> = _player2Name


    private val _turno = MutableLiveData<Boolean>()
    val turno: LiveData<Boolean> = _turno


    init {

    }






}