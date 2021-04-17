package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding
import kr.s10th24b.app.mylearningmate.viewmodel.ProfileViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
//    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java]}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        binding.apply {
//            viewModel = this@MainFragment.viewModel
//            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        binding.button2.setOnClickListener {
//            val action = MainFragmentDirections.actionMainFragmentToStateFragment()
//            requireView().findNavController().navigate(action)
//        }
        return binding.root
    }
}