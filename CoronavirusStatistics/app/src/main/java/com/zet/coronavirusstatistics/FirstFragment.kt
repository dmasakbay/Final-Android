package com.zet.coronavirusstatistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zet.coronavirusstatistics.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val vm: MainViewModel by activityViewModels()

    private var cAdapter: CountryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        initAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = cAdapter
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectCountryList()
        vm.getCountries()
//        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }

    private fun collectCountryList() = lifecycleScope.launchWhenStarted {
        vm.countries.collect {
            when (it) {
                is UiError -> {
                    // show error
                }
                is UiLoading -> {
                    //show loading
                }
                is UiSuccess -> {
                    // show success
                    cAdapter?.differ?.submitList(it.data)
                }
            }
        }
    }

    private fun initAdapter() {
        cAdapter = CountryAdapter()
        cAdapter?.setOnItemClickListener { movie ->
            movie.let {
                val bundle = bundleOf("movie" to it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}