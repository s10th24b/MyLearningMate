package kr.s10th24b.app.mylearningmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.s10th24b.app.mylearningmate.databinding.FragmentMainBinding


class LMFragment : Fragment() {
    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.bind(requireView()) }
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

        }
        return binding.root
    }
}