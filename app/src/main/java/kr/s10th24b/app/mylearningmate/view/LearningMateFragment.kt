package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.trello.rxlifecycle4.android.FragmentEvent
import com.trello.rxlifecycle4.components.support.RxFragment
import com.trello.rxlifecycle4.kotlin.bindUntilEvent
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kr.s10th24b.app.mylearningmate.adapters.LMRecyclerViewAdapter
import kr.s10th24b.app.mylearningmate.databinding.FragmentLearningMateBinding
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.model.Task
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel
import splitties.fragments.show
import splitties.toast.toast
import kotlin.random.Random

@AndroidEntryPoint
class LearningMateFragment : RxFragment(), AddTaskDialogFragment.AddTaskDialogListener {
    private val binding by lazy { FragmentLearningMateBinding.inflate(layoutInflater) }
    private val viewModel: LearningMateViewModel by viewModels()
    private val adapter: LMRecyclerViewAdapter by lazy { LMRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
//        toast("LearningMateFragment onCreate")
    }

    override fun onDestroyView() {
//        Log.d("KHJ","onDestroyView LearningMateFragment")
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        toast("LearningMateFragment onCreateView")
        // Inflate the layout for this fragment
        binding.apply {
            viewModel = this@LearningMateFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            lmRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            lmRecyclerView.adapter = adapter
        }
        viewModel.taskList.observe(viewLifecycleOwner) {
            Log.d("KHJ", "taskList.observe, task: $it")
            adapter.submitList(it)
        }
        binding.floatingActionButton.setOnClickListener {
//            viewModel.insertRandomTask()
            showAddTaskDialog()
        }
        adapter.removeButtonObservable
            .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
            .subscribe {
                toast(it.toString())
                viewModel.deleteTaskById(it)
            }

        return binding.root
    }

    fun showAddTaskDialog() {
        val dialog = AddTaskDialogFragment()
//        dialog.show(requireActivity().supportFragmentManager, "AddTaskDialogFragment")
        // 위처럼 하면, MainActivity에 Listener를 구현하고 거기서 써야한다. 하지만 나는 Fragment 안에서 쓰고싶기 때문에.
        // ProfileFragment에서 show 를 할 때 FragmentManager 문제인줄 알고, parentFragmentManager 와 childFragmentManager를 검색해보고
        // 지식을 쌓고 있었는데.. 우연히 한 티스토리에서 알게 되었다. show 가 문제가 아니라, 리스너 등록에서
        // 넘어온 context, 즉, 근본이 MainActivity 인 컨텍스트가 아니라 이 Dialog의 부모인 ProfileFragment를 가리키는
        // parentFragment를 캐스팅해야하는 것이었다....
        //https://jijs.tistory.com/entry/interface%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-DialogFragment%EC%9D%B4%EB%B2%A4%ED%8A%B8%EC%9D%98-%EA%B5%AC%ED%98%84%EB%B6%80%EB%A5%BC-%EB%8B%A4%EB%A5%B8-%EC%9E%A5%EC%86%8C%EC%97%90-%EA%B5%AC%ED%98%84%ED%95%9C%EB%8B%A4
        dialog.show(childFragmentManager, "AddTaskDialogFragment")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d("KHJ", "onDialogPositiveClick in Fragment")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d("KHJ", "onDialogNegativeClick in Fragment")
    }

    override fun onTaskProcessed(task: Task) {
        viewModel.insertTask(task)
    }
}