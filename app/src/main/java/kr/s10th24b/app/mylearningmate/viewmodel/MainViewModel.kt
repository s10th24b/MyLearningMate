package kr.s10th24b.app.mylearningmate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import splitties.toast.toast

class MainViewModel : ViewModel() {
    val count = MutableLiveData(0)
    fun increase() {
        count.value?.let{
            count.value = it+1
        }
    }
    fun decrease() {
        count.value?.let{
            count.value = it-1
        }
    }
}