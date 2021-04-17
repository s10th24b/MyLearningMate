package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle4.components.support.RxFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.databinding.FragmentLearningMateBinding
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel

@AndroidEntryPoint
class LearningMateFragment : RxFragment() {
    private val binding by lazy { FragmentLearningMateBinding.inflate(layoutInflater) }
    private val viewModel: LearningMateViewModel by viewModels()

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
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}