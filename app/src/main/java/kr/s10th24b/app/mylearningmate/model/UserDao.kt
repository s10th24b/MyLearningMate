package kr.s10th24b.app.mylearningmate.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao : BaseDao<User> {
    @Query("select * from users")
    abstract fun getAll(): List<User>
}