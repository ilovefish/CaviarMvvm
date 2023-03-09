package com.ilovefish.caviarmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * 避免取值的时候还要判空
 */
class FloatLiveData(value: Float = 0f) : MutableLiveData<Float>(value) {
    override fun getValue(): Float {
        return super.getValue()!!
    }
}