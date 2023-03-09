package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * 避免取值的时候还要判空
 */
class StringLiveData : MutableLiveData<String>() {

    override fun getValue(): String {
        return super.getValue() ?: ""
    }

}