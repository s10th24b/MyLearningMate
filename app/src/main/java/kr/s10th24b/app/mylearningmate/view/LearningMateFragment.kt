package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle4.components.support.RxFragment
import kr.s10th24b.app.mylearningmate.databinding.FragmentLearningMateBinding
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel

class LearningMateFragment : RxFragment() {
    private val binding by lazy { FragmentLearningMateBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[LearningMateViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding.apply {
            viewModel = this@LearningMateFragment.viewModel
            lifecycleOwner = this@LearningMateFragment
        }
        return binding.root
    }
}