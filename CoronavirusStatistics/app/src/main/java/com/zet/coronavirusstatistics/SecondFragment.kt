package com.zet.coronavirusstatistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zet.coronavirusstatistics.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        collectDetailCountry()
    }

    fun collectDetailCountry() = lifecycleScope.launchWhenStarted {
        vm.detailCountry.collect { result ->
            when (result) {
                is UiError -> {
                    Toast.makeText(requireContext(), "${result.message}", Toast.LENGTH_SHORT).show()
                }
                is UiLoading -> {}
                is UiSuccess -> {
                    val resultText = "County ${result.data.country}\n" +
                            "City ${result.data.city}\n" +
                            "Date ${result.data.date}\n" +
                            "Active ${result.data.active}\n" +
                            "Confirmed ${result.data.confirmed}\n" +
                            "Deaths ${result.data.deaths}\n" +
                            "Recovered ${result.data.recovered}\n"
                    binding.textviewSecond.text = resultText
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}