package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * 避免取值的时候还要判空
 */
class ShortLiveData : MutableLiveData<Short>() {
    override fun getValue(): Short {
        return super.getValue() ?: 0
    }
}