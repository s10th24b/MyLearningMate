package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle4.components.support.RxFragment
import kr.s10th24b.app.mylearningmate.databinding.FragmentProfileBinding
import kr.s10th24b.app.mylearningmate.viewmodel.ProfileViewModel

class ProfileFragment : RxFragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ProfileViewModel::class.java] }

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
            viewModel = this@ProfileFragment.viewModel
            lifecycleOwner = this@ProfileFragment
        }
        return binding.root
    }
}