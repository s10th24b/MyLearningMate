package kr.s10th24b.app.mylearningmate.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kr.s10th24b.app.mylearningmate.model.RoomHelper
import kr.s10th24b.app.mylearningmate.model.User
import splitties.init.appCtx

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val user by lazy {MutableLiveData(User())}
    init {
        val user = User()
        this.user.value = user
        val helper = RoomHelper.getInstance(application)
    }

    fun setUser(user: User) {
        this.user.value = user
    }

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    }
}