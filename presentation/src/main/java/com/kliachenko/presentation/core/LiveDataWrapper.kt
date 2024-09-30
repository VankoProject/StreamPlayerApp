package com.kliachenko.presentation.core

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface LiveDataWrapper {

    interface Read<T : Any> {
        fun liveData(): LiveData<T>
    }

    interface Update<T : Any> {
        fun update(value: T)
    }

    interface Mutable<T : Any> : Read<T>, Update<T>, Observe<T>

    abstract class Abstract<T : Any>(
        protected val liveData: MutableLiveData<T> = MutableLiveData(),
    ) : Mutable<T> {

        override fun liveData(): LiveData<T> {
            return liveData
        }

        override fun update(value: T) {
            Log.e("Player", "LiveDataWrapper update $value")
            liveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }

    }

    abstract class Post<T: Any>(
        protected val liveData: MutableLiveData<T> = MutableLiveData()
    ): Mutable<T> {

        override fun liveData(): LiveData<T> {
            return liveData
        }

        override fun update(value: T) {
            liveData.postValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

}

interface Observe<T : Any> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}
