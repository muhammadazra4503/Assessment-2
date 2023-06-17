package org.d3ifcool3046.assessment2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {
    @Insert
    fun insert(question: QuestionEntity): Long

    @Query("SELECT * FROM question")
    fun getallQuestion(): LiveData<List<QuestionEntity>>
}