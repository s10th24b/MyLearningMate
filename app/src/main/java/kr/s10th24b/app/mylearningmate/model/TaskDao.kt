package kr.s10th24b.app.mylearningmate.model

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface TaskDao : BaseDao<Task> {
    @Query("select * from tasks")
    abstract fun getAll(): Observable<List<Task>>

    @Query("select * from tasks where id=:id")
    abstract fun getTask(id: Long): Single<Task>

    @Query("delete from tasks where id=:id")
    abstract fun deleteTaskById(id: Long): Completable
}