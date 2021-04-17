package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle4.components.support.RxFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.databinding.FragmentStateBinding
import kr.s10th24b.app.mylearningmate.viewmodel.LearningMateViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.ProfileViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel

@AndroidEntryPoint
class StateFragment : RxFragment() {
    private val binding by lazy { FragmentStateBinding.inflate(layoutInflater) }
    private val viewModel: StateViewModel by viewModels()
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
            viewModel = this@StateFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}