package org.d3ifcool3046.assessment2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
abstract class QuizDb : RoomDatabase() {
    abstract val dao: QuestionDao
    companion object {
        @Volatile
        private var INSTANCE: QuizDb? = null
        fun getInstance(context: Context): QuizDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuizDb::class.java,
                        "quiz.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
