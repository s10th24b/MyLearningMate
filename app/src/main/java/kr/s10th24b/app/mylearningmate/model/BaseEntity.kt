package kr.s10th24b.app.mylearningmate.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

open class BaseEntity {
    @PrimaryKey(autoGenerate = true) @ColumnInfo var id: Long? = null
    @ColumnInfo var createDate:Long? = null
}