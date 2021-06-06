package kr.s10th24b.app.mylearningmate.utilities

import android.util.Log


object DevTool {
    const val TAG = "KHJ"
    fun logD(msg: String) {
        Log.d(TAG, msg)
    }

    fun logE(msg: String) {
        Log.e(TAG, msg)
    }
}