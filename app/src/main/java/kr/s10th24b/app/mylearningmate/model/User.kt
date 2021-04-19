package kr.s10th24b.app.mylearningmate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "users")
class User : BaseEntity(), Serializable{
    @ColumnInfo
    var userId = ""
    @ColumnInfo
    var name = ""
    @ColumnInfo
    var photoUrl = ""
    @ColumnInfo
    var affiliation = ""
    @ColumnInfo
    var age = 0
}