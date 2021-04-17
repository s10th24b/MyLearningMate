package kr.s10th24b.app.mylearningmate.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.internal.lifecycle.DefaultFragmentViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.s10th24b.app.mylearningmate.model.User
import kr.s10th24b.app.mylearningmate.model.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject internal constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val user = MutableLiveData<User>()
    private val mCompositeDatabase by lazy { CompositeDisposable() }

    init {
        val user = User()
        user.id = "allen246"
        user.name = "KHJ"
        user.affiliation = "Yonsei Univ."
        user.photoUrl = "@drawable/splash"
        this.user.value = user
    }

    fun setUser(user: User) {
        this.user.value = user
    }

    fun getUser(id: String) {
        mCompositeDatabase.add(
            userRepository.getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>() {
                    override fun onSuccess(t: User?) {
                        user.value = t as User
                    }

                    override fun onError(e: Throwable?) {
                        error("onError in getUser in ProfileViewModel")
                    }
                })
                )
    }

    fun getAllUser() {
        mCompositeDatabase.add(
            userRepository.getAllUser()
                .subscribeWith(object : DisposableObserver<List<User>>() {
                    override fun onNext(t: List<User>?) {
                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onComplete() {
                    }
                }
                )
        )
    }

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    }
}