package org.d3ifcool3046.assessment2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.d3ifcool3046.assessment2.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding : FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.tvScore.text = "Your score is ${args.correctAnswer} out of ${args.totalQuestion}"
        binding.finishButton.setOnClickListener{
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToMainFragment())
        }
        return binding.root
    }
}