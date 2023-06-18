package org.d3ifcool3046.assessment2.ui.main.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.db.QuestionDao
import org.d3ifcool3046.assessment2.db.QuestionEntity
import org.d3ifcool3046.assessment2.model.Question

class QuizViewModel(private val db: QuestionDao): ViewModel() {

    private val _questionList: MutableLiveData<List<Question>> = MutableLiveData()
    val questionList: LiveData<List<Question>> = _questionList
    val data = db.getallQuestion()

    init {
        viewModelScope.launch {
            val questionEntities = db.getallQuestion().value
            if (questionEntities != null) {
                val tempList = questionEntities.map { questionEntity ->
                    Question(
                        questionEntity.question,
                        questionEntity.image,
                        questionEntity.optionOne,
                        questionEntity.optionTwo,
                        questionEntity.optionThree,
                        questionEntity.optionFour,
                        questionEntity.correctAnswer
                    )
                }
                _questionList.postValue(tempList)
            }
        }
    }
    fun setQuestionList() {
        viewModelScope.launch {
            val tempList = mutableListOf<Question>()
            val question1 = Question(
                "Where does this country from?",
                R.drawable.id,
                "Singapore",
                "Monaco",
                "Indonesia",
                "Poland",
                3
            )
            tempList.add(question1)

            val question2 = Question(
                "Where does this country from?",
                R.drawable.ru,
                "Russia",
                "United State",
                "Serbia",
                "Finland",
                1
            )
            tempList.add(question2)

            val question3 = Question(
                "Where does this country from?",
                R.drawable.kr,
                "South Korea",
                "North Korea",
                "Japan",
                "China",
                1
            )
            tempList.add(question3)

            val question4 = Question(
                "Where does this country from?",
                R.drawable.ar,
                "Swedish",
                "Paraguay",
                "Argentina",
                "Greece",
                3
            )
            tempList.add(question4)

            val question5 = Question(
                "Where does this country from?",
                R.drawable.jp,
                "Switzerland",
                "Japan",
                "Taiwan",
                "China",
                2
            )
            tempList.add(question5)

            withContext(Dispatchers.IO) {
                for (question in tempList) {
                    val questionEntity = QuestionEntity(
                        question = question.question,
                        image = question.image,
                        optionOne = question.optionOne,
                        optionTwo = question.optionTwo,
                        optionThree = question.optionThree,
                        optionFour = question.optionFour,
                        correctAnswer = question.correctAnswer
                    )
                    db.insert(questionEntity)
                }
            }
            _questionList.postValue(tempList)
        }
    }
}