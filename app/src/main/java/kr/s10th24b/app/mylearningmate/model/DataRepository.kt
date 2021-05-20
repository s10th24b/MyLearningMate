package kr.s10th24b.app.mylearningmate.model

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject internal constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) {
    fun getUserRepository() = userRepository
    fun getTaskRepository() = taskRepository
}