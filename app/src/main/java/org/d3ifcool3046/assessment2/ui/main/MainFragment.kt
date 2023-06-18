package org.d3ifcool3046.assessment2.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.databinding.FragmentMainBinding
import org.d3ifcool3046.assessment2.ui.main.country.CountryViewModel


class MainFragment: Fragment() {
    lateinit var binding: FragmentMainBinding
    private val viewModel: CountryViewModel by lazy {
        ViewModelProvider(this)[CountryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Assessment 2"
        binding.listButton.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToListFragment())
        }
        binding.startButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToQuizFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.scheduleUpdater(requireActivity().application)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_mainFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}