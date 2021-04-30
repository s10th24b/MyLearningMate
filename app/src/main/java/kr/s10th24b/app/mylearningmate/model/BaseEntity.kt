package kr.s10th24b.app.mylearningmate.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

open class BaseEntity {
    @PrimaryKey(autoGenerate = true) @ColumnInfo var id: Long = 0L
    @ColumnInfo var createDate:Long? = null
}