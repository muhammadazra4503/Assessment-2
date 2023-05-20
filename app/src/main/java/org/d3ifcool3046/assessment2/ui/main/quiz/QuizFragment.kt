package org.d3ifcool3046.assessment2.ui.main.quiz

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.databinding.FragmentQuizBinding
import org.d3ifcool3046.assessment2.model.Question

class QuizFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentQuizBinding

    private val viewModel: QuizViewModel by lazy {
        ViewModelProvider(requireActivity())[QuizViewModel::class.java]
    }

    private var mCurrentPosition:Int = 1
    private var mOptionSelected:Int = 0
    private var mCorrectAnswer: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getData()
        binding.tvAnswer1.setOnClickListener(this)
        binding.tvAnswer2.setOnClickListener(this)
        binding.tvAnswer3.setOnClickListener(this)
        binding.tvAnswer4.setOnClickListener(this)
        binding.submit.setOnClickListener(this)
        setQuestion()
        super.onViewCreated(view, savedInstanceState)
    }

    fun setQuestion(){
        val question: Question? = viewModel.setQuestion()[mCurrentPosition -1]
        defaultOptionsView()
        if (mCurrentPosition == viewModel.setQuestion().size){
            binding.submit.text = "Finish"
        } else {
            binding.submit.text = "Submit"
        }
        binding.imageCountry.setImageResource(question!!.image)
        binding.questionText2.text = question!!.question
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

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_answer1 ->{
                selectedOptionView(binding.tvAnswer1, 1)
            }
            R.id.tv_answer2 ->{
                selectedOptionView(binding.tvAnswer2, 2)
            }
            R.id.tv_answer3 ->{
                selectedOptionView(binding.tvAnswer3, 3)
            }
            R.id.tv_answer4 ->{
                selectedOptionView(binding.tvAnswer4, 4)
            }
            R.id.submit ->{
                if(mOptionSelected == 0) {
                    mCurrentPosition ++
                when {
                    mCurrentPosition <= viewModel.setQuestion().size -> {
                        setQuestion()
                    }
                    else -> {
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToResultFragment(
                                mCorrectAnswer,
                                viewModel.setQuestion().size.toString()
                            )
                        )
                    }
                  }
                }else {
                    val question = viewModel.setQuestion().get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mOptionSelected){
                        answerView(
                        mOptionSelected,
                        R.drawable.wrong_border
                    )}
                    else{
                        mCorrectAnswer++
                        answerView(question.correctAnswer, R.drawable.correct_border)
                    }
                }
                if (mCurrentPosition == viewModel.setQuestion().size){
                    binding.submit.text = "Finish"
                }else{
                    binding.submit.text = "Go to next question"
                }
                mOptionSelected = 0
            }
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
    }
}

