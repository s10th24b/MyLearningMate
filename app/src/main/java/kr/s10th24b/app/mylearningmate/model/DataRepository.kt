package kr.s10th24b.app.mylearningmate.model

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DataRepository(application: Application) {
    val appDB = AppDatabase.getInstance(application)

    fun getUser(id: String): Single<User> {
        return appDB.userDao().getUser(id)
    }
    fun getAllUser(): Observable<List<User>> {
        return appDB.userDao().getAll()
    }

}