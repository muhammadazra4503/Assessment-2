package org.d3ifcool3046.assessment2.ui.main.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.model.Question

class QuizViewModel(): ViewModel() {
    private val question = MutableLiveData<List<Question?>>()

    init{
        question.value = setQuestion()
    }

    fun setQuestion(): List<Question>{
        val questionList = ArrayList<Question>()
        val question1 = Question(1,
            "Where does this country from?",
            R.drawable.id,
            "Singapore",
            "Monaco",
            "Indonesia",
            "Poland",
            3)
        questionList.add(question1)

        val question2 = Question(2,
            "Where does this country from?",
            R.drawable.au,
            "United Kingdom",
            "Barbados",
            "USA",
            "Australia",
            4)
        questionList.add(question2)

        val question3 = Question(3,
            "Where does this country from?",
            R.drawable.us,
            "USA",
            "Germany",
            "Belgium",
            "Swedish",
            1)
        questionList.add(question3)

        val question4 = Question(4,
            "Where does this country from?",
            R.drawable.jp,
            "Singapore",
            "Japan",
            "South Korea",
            "China",
            2)
        questionList.add(question4)

        val question5 = Question(5,
            "Where does this country from?",
            R.drawable.ar,
            "Argentina",
            "Spain",
            "Russia",
            "Netherlands",
            1)
        questionList.add(question5)

        return questionList
    }

    fun getData(): LiveData<List<Question?>> = question
}