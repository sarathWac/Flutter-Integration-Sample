package com.example.flutter.integration.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.flutter.integration.R
import com.example.flutter.integration.databinding.FragmentTransactionBinding
import com.example.flutter.integration.presentation.screen.main.MainState
import com.example.flutter.integration.presentation.screen.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        // Observe ViewModel state
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { binding.setState(it) }
            }
        }
    }

    private fun initView() {
        binding.ethAddressInputEditText.addTextChangedListener { text ->
            viewModel.updateAccountAddress(text.toString().trim())
        }

        binding.getBalanceButton.setOnClickListener {
            viewModel.getBalance()
            findNavController().navigate(R.id.FlutterLaunchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentTransactionBinding.setState(state: MainState) {
        when (state) {
            is MainState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                getBalanceButton.isEnabled = true
            }
            is MainState.Fetched -> {
                accountBalance.text = state.balance
                getBalanceButton.isEnabled = true
            }
            MainState.Loading -> Unit // getBalanceButton.isEnabled = false
        }
    }
}
