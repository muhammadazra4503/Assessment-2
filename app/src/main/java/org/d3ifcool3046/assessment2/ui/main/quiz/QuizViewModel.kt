package org.d3ifcool3046.assessment2.ui.main.quiz

import androidx.lifecycle.ViewModel
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.model.Question

class QuizViewModel(): ViewModel() {

    fun setQuestionList(): List<Question>{
        val questionList = ArrayList<Question>()
        val question1 = Question(
            "Where does this country from?",
            R.drawable.id,
            "Singapore",
            "Monaco",
            "Indonesia",
            "Poland",
            3)
        questionList.add(question1)

        val question2 = Question(
            "Where does this country from?",
            R.drawable.au,
            "United Kingdom",
            "Barbados",
            "USA",
            "Australia",
            4)
        questionList.add(question2)

        val question3 = Question(
            "Where does this country from?",
            R.drawable.us,
            "USA",
            "Germany",
            "Belgium",
            "Swedish",
            1)
        questionList.add(question3)

        val question4 = Question(
            "Where does this country from?",
            R.drawable.jp,
            "Singapore",
            "Japan",
            "South Korea",
            "China",
            2)
        questionList.add(question4)

        val question5 = Question(
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
}