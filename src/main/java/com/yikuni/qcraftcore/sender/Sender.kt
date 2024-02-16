package com.yikuni.qcraftcore.sender

import com.yikuni.qcraftcore.QCraftCore

interface Sender {
    fun info(msg:String)
    fun warn(msg:String)
    fun error(msg:String)
    fun success(msg:String)
    fun primary(msg:String)
    fun debug(msg: String){
        val boolean = QCraftCore.DEBUG
        if (boolean) info(msg)
    }
}