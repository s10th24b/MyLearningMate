package kr.s10th24b.app.mylearningmate.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle4.components.support.RxFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Single
import kr.s10th24b.app.mylearningmate.databinding.FragmentProfileBinding
import kr.s10th24b.app.mylearningmate.viewmodel.ProfileViewModel
import kr.s10th24b.app.mylearningmate.viewmodel.StateViewModel

@AndroidEntryPoint
class ProfileFragment : RxFragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    val viewModel: ProfileViewModel by viewModels()

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
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.getAllUser()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.nameText.text = it.name
            binding.affiliationText.text = it.affiliation
            binding.photoImage.setImageURI(Uri.parse(it.photoUrl))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}