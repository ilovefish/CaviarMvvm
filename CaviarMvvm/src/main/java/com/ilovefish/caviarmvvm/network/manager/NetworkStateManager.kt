package com.ilovefish.caviarmvvm.network.manager

import com.ilovefish.caviarmvvm.callback.livedata.event.EventLiveData

class NetworkStateManager private constructor() {

    val mNetStateLiveData = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}