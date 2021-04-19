package kr.s10th24b.app.mylearningmate.model

import android.app.Application
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject internal constructor(
    private val userDao: UserDao
) {
    fun getUser(id: String): Single<User> {
        return userDao.getUser(id)
    }
    fun getAllUser(): Observable<List<User>> {
        return userDao.getAll()
    }
    fun addUser(user: User) {
        userDao.insert(user)
    }
    fun updateUser(user: User) {
        userDao.update(user)
    }
    fun deleteUser(user: User) {
        userDao.delete(user)
    }
}
