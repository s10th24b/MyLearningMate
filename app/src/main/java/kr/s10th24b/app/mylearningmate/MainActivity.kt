package kr.s10th24b.app.mylearningmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.view.clicks
import com.trello.rxlifecycle4.android.ActivityEvent
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import com.trello.rxlifecycle4.kotlin.bindUntilEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kr.s10th24b.app.mylearningmate.databinding.ActivityMainBinding
import kr.s10th24b.app.mylearningmate.viewmodel.MainViewModel

class MainActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}