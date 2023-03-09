package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * 避免取值的时候还要判空
 */
class ByteLiveData : MutableLiveData<Byte>() {
    override fun getValue(): Byte {
        return super.getValue() ?: 0
    }
}