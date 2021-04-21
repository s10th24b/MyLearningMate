package kr.s10th24b.app.mylearningmate.adapters

import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object BindingConversions {
    @BindingAdapter("numberPickerValue")
    @JvmStatic
    internal fun setNumberPickerValue(view: NumberPicker, value: Int) {
        view.value = value
    }

    @InverseBindingAdapter(attribute = "numberPickerValue")
    @JvmStatic
    internal fun getNumberPickerValue(view: NumberPicker): Int = view.value

    @BindingAdapter("numberPickerValueAttrChanged")
    @JvmStatic
    internal fun setValueListener(view: NumberPicker, attrChange: InverseBindingListener) {
        Log.d("KHJ","value changed. ${view.value} id: ${view.id}")
//        Toast.makeText(view.context,"value changed",Toast.LENGTH_SHORT).show()

    }

    @BindingAdapter("numberPickerMinValue")
    @JvmStatic
    internal fun setNumberPickerMinValue(view: NumberPicker, minValue: Int) {
        view.minValue = minValue
    }

    @InverseBindingAdapter(attribute = "numberPickerMinValue")
    @JvmStatic
    internal fun getNumberPickerMinValue(view: NumberPicker): Int = view.minValue

    @BindingAdapter("numberPickerMinValueAttrChanged")
    @JvmStatic
    internal fun setMinValueListener(view: NumberPicker, attrChange: InverseBindingListener) {
        Log.d("KHJ","minValue changed. ${view.minValue} id: ${view.id}")
    }

    @BindingAdapter("numberPickerMaxValue")
    @JvmStatic
    internal fun setNumberPickerMaxValue(view: NumberPicker, maxValue: Int) {
        view.maxValue = maxValue
    }

    @InverseBindingAdapter(attribute = "numberPickerMaxValue")
    @JvmStatic
    internal fun getNumberPickerMaxValue(view: NumberPicker): Int = view.maxValue

    @BindingAdapter("numberPickerMaxValueAttrChanged")
    @JvmStatic
    internal fun setMaxValueListener(view: NumberPicker, attrChange: InverseBindingListener) {
        Log.d("KHJ","minValue changed. ${view.maxValue} id: ${view.id}")
    }
}