package kr.s10th24b.app.mylearningmate.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.adapters.NumberPickerBindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.s10th24b.app.mylearningmate.model.DataRepository
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class AddTaskDialogViewModel @Inject internal constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    val subject = MutableLiveData<String>()
    val probCount = MutableLiveData<Int>()
    val hour = MutableLiveData<Int>()
    val minute = MutableLiveData<Int>()

    fun initialize(subject: String, probCount: Int, hour: Int, minute: Int) {
        this.subject.value = subject
        this.probCount.value = probCount
        this.hour.value = hour
        this.minute.value = minute
    }
}