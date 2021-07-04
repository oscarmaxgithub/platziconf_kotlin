package com.platzi.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 2/07/2021
 */
const val  CONFERENCES_COLLECTION_NAME="conferences"
const val  SPEAKERS_COLLECTION_NAME="speakers"

class FirestoreService {
    val firebaseFirestore=FirebaseFirestore.getInstance()
    //HABILITAR LA PERSISTENCIA DE DATOS EN LOCAL
    val settings =FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings=settings
    }

    fun getSpeakers(callback:Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result->
                for (doc in result){
                    val list= result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback:Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result->
                for (doc in result){
                    val list= result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}