package com.example.flutter.integration.presentation.screen.flutter_launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flutter.integration.data.AndroidFlutterEngine
import com.example.flutter.integration.databinding.FragmentFlutterLaunchBinding
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.android.FlutterActivity
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class FlutterLaunchFragment : Fragment() {

    private var _binding: FragmentFlutterLaunchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var  androidFlutterEngine: AndroidFlutterEngine

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlutterLaunchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flutterLaunchButton.setOnClickListener {
            startActivity(
                FlutterActivity
                    .withCachedEngine(androidFlutterEngine.engineId)
                    .build(this.requireContext())
            )
        }

        /**
         * Set up a MethodChannel handler.
         * This handler is responsible for handling method calls from Flutter.
         *
         * Here we can send/receive data from flutter
         */
        androidFlutterEngine.getChannel().setMethodCallHandler { call, result ->
            when (FlutterChannelAction.getAction(call.method)) {
                FlutterChannelAction.SEND_DATA -> {
                    /*val account = AccountBalanceDto(
                        account = "dummyAccount",
                        balance = "20000000"
                    )
                    val data = Json.encodeToString<AccountBalanceDto>(account)*/
                    // Send data to Flutter
                    result.success("Hello from Android/Native")
                }

                FlutterChannelAction.RECEIVE_DATA -> {
                    // Receive Data from the flutter module
                    val data = call.arguments as String
                    println("Received data from Flutter: $data")
                    result.success("Data received on native side")
                    binding.flutterResultTextView.text = data
                }

                FlutterChannelAction.NONE -> result.notImplemented()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        androidFlutterEngine.cleanup()
    }
}