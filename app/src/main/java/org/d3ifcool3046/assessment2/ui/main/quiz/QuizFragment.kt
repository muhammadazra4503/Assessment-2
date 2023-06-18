package org.d3ifcool3046.assessment2.ui.main.quiz

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.databinding.FragmentQuizBinding
import org.d3ifcool3046.assessment2.db.QuizDb
import org.d3ifcool3046.assessment2.model.Question

class QuizFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var mCurrentPosition: Int = 1
    private var mOptionSelected: Int = 0
    private var mCorrectAnswer: Int = 0
    private val viewModel: QuizViewModel by lazy {
        val db = QuizDb.getInstance(requireContext())
        val factory = QuizViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[QuizViewModel::class.java]
    }
    private var mQuestionList: MutableList<Question>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Assessment 2"
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvAnswer1.setOnClickListener(this)
        binding.tvAnswer2.setOnClickListener(this)
        binding.tvAnswer3.setOnClickListener(this)
        binding.tvAnswer4.setOnClickListener(this)
        binding.submit.setOnClickListener(this)

        // Observe the questionList LiveData
        viewModel.questionList.observe(viewLifecycleOwner) { questionList ->
            if (questionList.isNotEmpty()) {
                mQuestionList = questionList.toMutableList() // Initialize mQuestionList here
                setQuestion()
            }
        }
        // Call setQuestionList() to populate the question list
        viewModel.setQuestionList()
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_answer1 -> {
                selectedOptionView(binding.tvAnswer1, 1)
            }
            R.id.tv_answer2 -> {
                selectedOptionView(binding.tvAnswer2, 2)
            }
            R.id.tv_answer3 -> {
                selectedOptionView(binding.tvAnswer3, 3)
            }
            R.id.tv_answer4 -> {
                selectedOptionView(binding.tvAnswer4, 4)
            }
            R.id.submit -> {
                if (mOptionSelected != 0) { // Check if an option is selected
                    val question = mQuestionList?.getOrNull(mCurrentPosition - 1)
                    if (question?.correctAnswer != mOptionSelected) {
                        answerView(mOptionSelected, R.drawable.wrong_border)
                    } else {
                        mCorrectAnswer++
                        answerView(question.correctAnswer, R.drawable.correct_border)
                    }
                    if (mCurrentPosition == mQuestionList?.size) {
                        binding.submit.text = "Finish"
                    } else {
                        binding.submit.text = "Go to next question"
                    }
                    mOptionSelected = 0
                } else {
                    mCurrentPosition++
                    if (mCurrentPosition <= mQuestionList?.size ?: 0) {
                        setQuestion()
                    } else {
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToResultFragment(
                                mCorrectAnswer,
                                mQuestionList?.size.toString() ?: "0"
                            )
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setQuestion() {
        if (mQuestionList.isNullOrEmpty()) {
            return // Exit the function if mQuestionList is null or empty
        }
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size) {
            binding.submit.text = "Submit"
        } else {
            binding.submit.text = "Submit"
        }
        binding.imageCountry.setImageResource(question.image)
        binding.questionText2.text = question.question
        binding.tvAnswer1.text = question.optionOne
        binding.tvAnswer2.text = question.optionTwo
        binding.tvAnswer3.text = question.optionThree
        binding.tvAnswer4.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvAnswer1)
        options.add(1, binding.tvAnswer2)
        options.add(2, binding.tvAnswer3)
        options.add(3, binding.tvAnswer4)
        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_border
            )
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.tvAnswer1.background =
                ContextCompat.getDrawable(requireContext(), drawableView)
            2 -> binding.tvAnswer2.background =
                ContextCompat.getDrawable(requireContext(), drawableView)
            3 -> binding.tvAnswer3.background =
                ContextCompat.getDrawable(requireContext(), drawableView)
            4 -> binding.tvAnswer4.background =
                ContextCompat.getDrawable(requireContext(), drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mOptionSelected = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_border
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
