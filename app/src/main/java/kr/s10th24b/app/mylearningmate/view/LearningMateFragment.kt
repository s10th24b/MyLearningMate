package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.trello.rxlifecycle4.android.FragmentEvent
import com.trello.rxlifecycle4.components.support.RxFragment
import com.trello.rxlifecycle4.kotlin.bindUntilEvent
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.adapters.LMRecyclerViewAdapter
import kr.s10th24b.app.mylearningmate.databinding.FragmentLearningMateBinding
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.model.Task
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel
import splitties.toast.toast
import kotlin.random.Random

@AndroidEntryPoint
class LearningMateFragment : RxFragment() {
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
            viewModel.insertRandomTask()
        }
        adapter.removeButtonObservable
            .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
            .subscribe {
                toast(it)
                viewModel.deleteTask(task)
            }

        return binding.root
    }
}