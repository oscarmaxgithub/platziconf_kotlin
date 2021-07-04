package com.platzi.conf.model

import java.io.Serializable
import java.util.*

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 2/07/2021
 */
class Conference :Serializable{
    lateinit var title:String
    lateinit var description:String
    lateinit var tag:String
    lateinit var datetime:Date
    lateinit var speaker: String
}