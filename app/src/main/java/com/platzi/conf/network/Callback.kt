package com.platzi.conf.network

import java.lang.Exception

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 2/07/2021
 */
interface Callback<T> {
    fun onSuccess(result:T?)

    fun onFailed(exceptio:Exception)
}