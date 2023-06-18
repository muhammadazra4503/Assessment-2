package org.d3ifcool3046.assessment2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    var question: String,
    var image: Int,
    var optionOne: String,
    val optionTwo: String,
    var optionThree: String,
    var optionFour: String,
    var correctAnswer: Int,
)
