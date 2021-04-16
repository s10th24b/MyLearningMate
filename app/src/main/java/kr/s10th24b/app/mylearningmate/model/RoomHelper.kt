package kr.s10th24b.app.mylearningmate.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: RoomDatabase? = null
        fun getInstance(context: Context): RoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    RoomDatabase::class.java, "mlm_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as RoomDatabase
        }
    }
}