package kr.s10th24b.app.mylearningmate.viewmodel

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kr.s10th24b.app.mylearningmate.model.User

class ProfileViewModel : ViewModel() {
    val user= MutableLiveData(User())

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