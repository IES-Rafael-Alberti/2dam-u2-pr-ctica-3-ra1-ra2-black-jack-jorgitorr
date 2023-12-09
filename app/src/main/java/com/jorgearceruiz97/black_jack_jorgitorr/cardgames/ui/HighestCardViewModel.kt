package com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Baraja
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Carta

class HighestCardViewModel(application:Application): AndroidViewModel(application){

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc : LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Carta>()


    init {
        restart()
    }

    fun getCard(){
        _card.value = Baraja.dameCarta()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = "${_card.value?.nombre} de ${_card.value?.palo}"
    }



    fun restart() {
        Baraja.crearBaraja(context)
        Baraja.barajar()
        _card.value = Baraja.dameCartaBocaAbajo()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
    }

    fun getCardsTotal(): String {
        return Baraja.tamanioBaraja().toString()
    }

    fun btnGetCardEnabled() = Baraja.tamanioBaraja() > 1

    fun btnResetDeckOfCardsEnabled() = Baraja.tamanioBaraja() < 52


}