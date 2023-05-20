package org.d3ifcool3046.assessment2.model

data class Question(
    var id: Int,
    var question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    var correctAnswer: Int
)


