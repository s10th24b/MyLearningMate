package kr.s10th24b.app.mylearningmate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "tasks")
class Task(
    @ColumnInfo var subject: String = "",
    @ColumnInfo var problemCount: Int = 1,
    @ColumnInfo var time: String = ""
) : BaseEntity(), Serializable {
    override fun equals(other: Any?): Boolean {
        if (other !is Task) return false
        return (subject == other.subject && problemCount == other.problemCount &&
                time == other.time)
    }
}
