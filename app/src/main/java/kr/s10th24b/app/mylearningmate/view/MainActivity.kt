package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import kr.s10th24b.app.mylearningmate.R
import kr.s10th24b.app.mylearningmate.databinding.ActivityMainBinding
import kr.s10th24b.app.mylearningmate.viewmodel.MainViewModel

class MainActivity : RxAppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyLearningMate)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)
    }
}