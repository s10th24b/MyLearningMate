package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
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
//            viewLifecycleOwner = this@MainFragment
        }
//        binding.button2.setOnClickListener {
//            val action = MainFragmentDirections.actionMainFragmentToStateFragment()
//            requireView().findNavController().navigate(action)
//        }
        return binding.root
    }
}