package kr.s10th24b.app.mylearningmate.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import splitties.toast.toast

class MainViewModel : ViewModel() {
    val ob = PublishSubject.create<String>()
    var count = "0"
    fun increase() {
        toast("increase")
        count = (count.toInt()+1).toString()
    }
    fun decrease() {
        toast("increase")
        count = (count.toInt()-1).toString()
    }
}