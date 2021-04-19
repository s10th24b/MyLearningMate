package kr.s10th24b.app.mylearningmate.model

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao : BaseDao<User> {
    @Query("select * from users")
    abstract fun getAll(): Observable<List<User>>

    @Query("select * from users where id=:id")
    abstract fun getUser(id: Long): Single<User>
}