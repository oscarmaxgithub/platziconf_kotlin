package com.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 3/07/2021
 */
class SpeakerViewModel:ViewModel() {
    val firestoreService= FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading= MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinish()
            }

            override fun onFailed(exceptio: Exception) {
                processFinish()
            }
        })
    }

    private fun processFinish() {
        isLoading.value=true
    }
}