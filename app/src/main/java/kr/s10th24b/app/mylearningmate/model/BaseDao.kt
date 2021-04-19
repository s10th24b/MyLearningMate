package kr.s10th24b.app.mylearningmate.model

import androidx.room.*
import io.reactivex.rxjava3.core.Completable

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T): Completable

    @Update
    fun update(obj: T): Completable

    @Delete()
    fun delete(obj: T): Completable
}