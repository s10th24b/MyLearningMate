package kr.s10th24b.app.mylearningmate.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kr.s10th24b.app.mylearningmate.R
import kr.s10th24b.app.mylearningmate.databinding.ActivityMainBinding
import kr.s10th24b.app.mylearningmate.model.DataRepository
import kr.s10th24b.app.mylearningmate.viewmodel.MainViewModel
import splitties.toast.toast

@AndroidEntryPoint
class MainActivity : RxAppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyLearningMate)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.stateFragment,R.id.profileFragment,R.id.learningMateFragment), binding.mainDrawerLayout)
//        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.mainDrawerLayout)
        binding.mainToolbar.setupWithNavController(navController,appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController,appBarConfiguration)
    }

}