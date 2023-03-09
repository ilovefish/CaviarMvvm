package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * 避免取值的时候还要判空
 */
class BooleanLiveData : MutableLiveData<Boolean>() {

    override fun getValue(): Boolean {
        return super.getValue() ?: false
    }
}

