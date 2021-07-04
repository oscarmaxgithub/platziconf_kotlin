package com.platzi.conf.view.adapter

import com.platzi.conf.model.Speaker

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 3/07/2021
 */
interface SpeakerListener {
    fun onSpeakerOnClicked(speaker: Speaker, pos:Int)
}