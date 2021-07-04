package com.platzi.conf.view.adapter

import com.platzi.conf.model.Conference

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 3/07/2021
 */
interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, pos:Int)
}