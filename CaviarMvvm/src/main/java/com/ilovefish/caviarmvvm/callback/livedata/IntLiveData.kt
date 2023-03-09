package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * 避免取值的时候还要判空
 */
class IntLiveData : MutableLiveData<Int>() {

    override fun getValue(): Int {
        return super.getValue() ?: 0
    }
}