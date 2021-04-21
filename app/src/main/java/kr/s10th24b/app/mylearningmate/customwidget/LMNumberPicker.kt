package kr.s10th24b.app.mylearningmate.customwidget

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import kr.s10th24b.app.mylearningmate.R

class LMNumberPicker : NumberPicker {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typed = context.obtainStyledAttributes(attrs, R.styleable.LMNumberPicker)

        for (i in 0 until typed.indexCount) {
            if (typed.getIndex(i) == R.styleable.LMNumberPicker_value) {
                value = typed.getInt(typed.getIndex(i), 0)
            }
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {

    }

}