package kr.s10th24b.app.mylearningmate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.s10th24b.app.mylearningmate.model.DataRepository
import javax.inject.Inject

@HiltViewModel
class AddTaskDialogViewModel @Inject internal constructor(
    private val dataRepository: DataRepository
): ViewModel() {
    val subject = MutableLiveData<String>()
    val probCount = MutableLiveData<Int>()
    val hour = MutableLiveData<Int>()
    val minute = MutableLiveData<Int>()
}