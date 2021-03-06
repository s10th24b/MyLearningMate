package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trello.rxlifecycle4.android.FragmentEvent
import com.trello.rxlifecycle4.components.support.RxFragment
import com.trello.rxlifecycle4.kotlin.bindUntilEvent
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.R
import kr.s10th24b.app.mylearningmate.adapters.LMRecyclerViewAdapter
import kr.s10th24b.app.mylearningmate.databinding.FragmentLearningMateBinding
import kr.s10th24b.app.mylearningmate.model.Task
import kr.s10th24b.app.mylearningmate.utilities.DevTool.logD
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import splitties.toast.toast

@AndroidEntryPoint
class LearningMateFragment : RxFragment(), AddTaskDialogFragment.AddTaskDialogListener {
    private val binding by lazy { FragmentLearningMateBinding.inflate(layoutInflater) }
    private val viewModel: LearningMateViewModel by viewModels()
    private val adapter by lazy { LMRecyclerViewAdapter() }
    private lateinit var selectedTask: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
//        toast("LearningMateFragment onCreate")
    }

    override fun onDestroyView() {
//        logD("onDestroyView LearningMateFragment")
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        toast("LearningMateFragment onCreateView")
        binding.apply {
            viewModel = this@LearningMateFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            lmRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            lmRecyclerView.adapter = adapter
        }
        viewModel.taskList.observe(viewLifecycleOwner) {
            logD( "taskList.observe, task: $it")
            adapter.submitList(it)
        }
        binding.floatingActionButton.setOnClickListener {
            showAddTaskDialog(mode = "??????")
        }
        adapter.setListAdapterListener(object : LMRecyclerViewAdapter.ListAdapterListener {
            override fun onRemoveButtonClicked(task: Task) {
                viewModel.deleteTask(task)
            }

            override fun onMenuOpened(task: Task) {
//                toast("onMenuOpened")
                selectedTask = task
            }
        })

        return binding.root
    }

    private fun showAddTaskDialog(
        mode: String = "??????",
        subject: String = "",
        probCount: Int = 1,
        hour: Int = 1,
        minute: Int = 0
    ) {
        val dialog = AddTaskDialogFragment(mode, subject, probCount, hour, minute)
//        dialog.show(requireActivity().supportFragmentManager, "AddTaskDialogFragment")
        // ????????? ??????, MainActivity??? Listener??? ???????????? ????????? ????????????. ????????? ?????? Fragment ????????? ???????????? ?????????.
        // ProfileFragment?????? show ??? ??? ??? FragmentManager ???????????? ??????, parentFragmentManager ??? childFragmentManager??? ???????????????
        // ????????? ?????? ????????????.. ????????? ??? ?????????????????? ?????? ?????????. show ??? ????????? ?????????, ????????? ????????????
        // ????????? context, ???, ????????? MainActivity ??? ??????????????? ????????? ??? Dialog??? ????????? LearningMateFragment ??? ????????????
        // parentFragment??? ????????????????????? ????????????....
        //https://jijs.tistory.com/entry/interface%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-DialogFragment%EC%9D%B4%EB%B2%A4%ED%8A%B8%EC%9D%98-%EA%B5%AC%ED%98%84%EB%B6%80%EB%A5%BC-%EB%8B%A4%EB%A5%B8-%EC%9E%A5%EC%86%8C%EC%97%90-%EA%B5%AC%ED%98%84%ED%95%9C%EB%8B%A4
        dialog.show(childFragmentManager, "AddTaskDialogFragment")
    }

    private fun showAddTaskDialog(mode: String = "??????", task: Task) {
        val dialog = AddTaskDialogFragment(mode, task)
        dialog.show(childFragmentManager, "AddTaskDialogFragment")
    }

    override fun onDialogPositiveClick(
        dialog: DialogFragment,
        task: Task?,
        mode: String,
        completed: Boolean
    ) {
        if (completed) {
            when (mode) {
                "??????" -> viewModel.insertTask(task as Task)
                "??????" -> viewModel.updateTask(task as Task)
                else -> toast("$mode else in onDialogPositiveClick()")
            }
        } else toast("???????????? ?????? Task ?????????. ???????????? ??????????????????")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        logD( "onDialogNegativeClick in Fragment")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.lmCardContextMenuModify -> {
                showAddTaskDialog(mode = "??????", task = selectedTask)
                true
            }
            R.id.lmCardContextMenuDelete -> {
                viewModel.deleteTask(selectedTask)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}