package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * 避免取值的时候还要判空
 */
class DoubleLiveData : MutableLiveData<Double>() {
    override fun getValue(): Double {
        return super.getValue() ?: 0.0
    }
}