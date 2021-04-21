package kr.s10th24b.app.mylearningmate.adapters

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData

object BindingConversions {
//    @BindingAdapter("numberPickerValue")
//    @JvmStatic
//    internal fun setNumberPickerValue(view: NumberPicker, value: MutableLiveData<Int>) {
//        value.value?.let {
//            view.value = it
//        }
//    }

    @InverseBindingAdapter(attribute = "android:value")
    @JvmStatic
    internal fun getNumberPickerValue(view: NumberPicker): Int = view.value



    @BindingAdapter("app:minValue")
    @JvmStatic
    internal fun setNumberPickerMinValue(view: NumberPicker, value: Int) {
        view.minValue = value
    }

    @InverseBindingAdapter(
        attribute = "app:minValue"
    )
    @JvmStatic
    internal fun getNumberPickerMinValue(view: NumberPicker): Int = view.minValue
//
//    @BindingAdapter("numberPickerMinValueAttrChanged")
//    @JvmStatic
//    fun setChangeListener(view: NumberPicker, valueAttrChanged: InverseBindingListener) {
//        view.setOnValueChangedListener { picker, oldVal, newVal ->
//            valueAttrChanged.onChange()
//        }
//    }


    @BindingAdapter("app:maxValue")
    @JvmStatic
    internal fun setNumberPickerMaxValue(view: NumberPicker, value: Int) {
        view.maxValue = value
    }
}