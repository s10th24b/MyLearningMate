package kr.s10th24b.app.mylearningmate.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.trello.rxlifecycle4.components.support.RxDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.databinding.AddTaskViewBinding
import kr.s10th24b.app.mylearningmate.model.Task
import kr.s10th24b.app.mylearningmate.viewmodel.AddTaskDialogViewModel
import splitties.systemservices.subscriptionManager
import splitties.toast.toast
import java.lang.ClassCastException
import java.lang.IllegalStateException
import kotlin.math.min

@AndroidEntryPoint
class AddTaskDialogFragment(
    var mode: String = "추가",
    var subject: String = "",
    var probCount: Int = 1,
    var hour: Int = 1,
    var minute: Int = 0
) :
    RxDialogFragment() {

    constructor(mode: String, task: Task) : this() {
        this.mode = mode
        this.subject = task.subject
        this.probCount = task.problemCount
        this.hour = task.time.substring(0, 2).toInt()
        this.minute = task.time.substring(3).toInt()
        this.existTaskFlag = true
        this.existTask = task
        this.existTaskId = task.id
    }

    lateinit var listener: AddTaskDialogListener
    private var existTaskFlag = false
    private var existTask = Task()
    private var existTaskId = 0L
    val viewModel: AddTaskDialogViewModel by viewModels()

    interface AddTaskDialogListener {
        fun onDialogPositiveClick(
            dialog: DialogFragment,
            task: Task?,
            mode: String,
            completed: Boolean
        )

        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    val binding: AddTaskViewBinding by lazy { AddTaskViewBinding.inflate(layoutInflater) }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("KHJ", "onCreateDialog")
        return requireActivity().let {
            // User the Builder class for convenient dialog construction
//            val builder = AlertDialog.Builder(requireParentFragment().context)
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("작성") { dialog, which ->
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
                        if (existTaskFlag) task.id = existTaskId
                        listener.onDialogPositiveClick(this@AddTaskDialogFragment, task, mode, true)
                    } else listener.onDialogPositiveClick(
                        this@AddTaskDialogFragment,
                        null,
                        mode,
                        false
                    )
                }
                setNegativeButton("취소") { dialog, which ->
                    Log.d("KHJ", "취소 버튼 클릭")
                    listener.onDialogNegativeClick(this@AddTaskDialogFragment)
                }
                setTitle("Task $mode")
                setView(binding.root)
//                setMessage("Task 추가 MSG")
            }
            builder.create()
        } ?: throw IllegalStateException("Fragment cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("KHJ", "onCreateView")
        viewModel.subject.observe(this) {
//            toast("viewModel.subject: $it")
        }
        viewModel.probCount.observe(this) {
//            toast("viewModel.probCount: $it")
        }
        viewModel.hour.observe(this) {
//            toast("viewModel.hour: $it")
        }
        viewModel.minute.observe(this) {
//            toast("viewModel.minute: $it")
        }
        binding.apply {
            viewModel = this@AddTaskDialogFragment.viewModel
            lifecycleOwner = this@AddTaskDialogFragment
            val numPickerFormatter = NumberPicker.Formatter { if (it < 10) "0$it" else "$it" }
            hourPicker.setFormatter(numPickerFormatter)
            minutePicker.setFormatter(numPickerFormatter)
        }
        viewModel.initialize(subject, probCount, hour, minute)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment as AddTaskDialogListener
//            toast("context: $context")
//            toast("listener parentFragment: $parentFragment")
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement AddTaskDialogListener"))
        }
    }
}