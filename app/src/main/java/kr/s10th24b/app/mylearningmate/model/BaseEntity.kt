package kr.s10th24b.app.mylearningmate.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

open class BaseEntity {
    @PrimaryKey @ColumnInfo var id: String = ""
    @ColumnInfo var createDate:Long? = null
}