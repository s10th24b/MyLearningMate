package kr.s10th24b.app.mylearningmate.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.loader.content.AsyncTaskLoader
import kr.s10th24b.app.mylearningmate.R
import kr.s10th24b.app.mylearningmate.databinding.AddTaskViewBinding
import kr.s10th24b.app.mylearningmate.model.Task
import splitties.toast.toast
import java.lang.ClassCastException
import java.lang.IllegalStateException
import kotlin.math.min

class AddTaskDialogFragment : DialogFragment() {
    lateinit var listener: AddTaskDialogListener

    interface AddTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: Task?, completed: Boolean)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    val binding: AddTaskViewBinding by lazy { AddTaskViewBinding.inflate(layoutInflater) }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            // User the Builder class for convenient dialog construction
//            val builder = AlertDialog.Builder(requireParentFragment().context)
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setView(binding.root)
                setPositiveButton("추가", DialogInterface.OnClickListener { dialog, which ->
                    // User Clicked OK Button
                    Log.d("KHJ", "추가 버튼 클릭")
                    val subject = binding.subjectText.text.toString().trim()
                    val problemCount = binding.probCountNumberPicker.value
                    var hour = binding.hourPicker.value.toString()
                    if (hour.length < 2) hour = "0$hour"
                    var minute = binding.minutePicker.value.toString()
                    if (minute.length < 2) minute = "0$minute"
                    val time = "$hour:$minute"
                    if (subject.isNotBlank() && (hour != "00" || minute != "00")) {
                        val task = Task(subject, problemCount, time)
                        listener.onDialogPositiveClick(this@AddTaskDialogFragment, task, true)
                    } else listener.onDialogPositiveClick(this@AddTaskDialogFragment, null, false)
                })
                setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                    Log.d("KHJ", "취소 버튼 클릭")
                    listener.onDialogNegativeClick(this@AddTaskDialogFragment)
                })
                setTitle("Task 추가")
//                setMessage("Task 추가 MSG")
            }
            binding.probCountNumberPicker.minValue = 1
            binding.probCountNumberPicker.maxValue = 3000
            binding.hourPicker.minValue = 0
            binding.hourPicker.maxValue = 59
            binding.minutePicker.minValue = 0
            binding.minutePicker.maxValue = 59
            val numPickerFormatter =
                NumberPicker.Formatter { if (it < 10) "0$it" else "$it" }
            binding.hourPicker.setFormatter(numPickerFormatter)
            binding.minutePicker.setFormatter(numPickerFormatter)
            builder.create()
        } ?: throw IllegalStateException("Fragment cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment as AddTaskDialogFragment.AddTaskDialogListener
//            toast("context: $context")
//            toast("listener parentFragment: $parentFragment")
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement AddTaskDialogListener"))
        }
    }
}