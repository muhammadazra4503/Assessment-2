package org.d3ifcool3046.assessment2.ui.main.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool3046.assessment2.db.QuestionDao

class QuizViewModelFactory(
    private val db: QuestionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}