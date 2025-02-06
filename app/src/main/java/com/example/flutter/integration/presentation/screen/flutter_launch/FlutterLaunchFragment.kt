package com.example.flutter.integration.presentation.screen.flutter_launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flutter.integration.databinding.FragmentFlutterLaunchBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val ENGINE_ID = "sample_flutter_engine_id"
private const val CHANNEL = "com.example.app/channel"


class FlutterLaunchFragment : Fragment() {

    private var _binding: FragmentFlutterLaunchBinding? = null
    private val binding get() = _binding!!
    private lateinit var channel: MethodChannel


    private val flutterEngine: FlutterEngine by lazy {
        FlutterEngine(requireContext()).apply {
            navigationChannel.setInitialRoute("/sendReceive")
            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        // Cache the FlutterEngine to be used by FlutterActivity.
        // Could be created only when we are sure the user going to navigate to module
        FlutterEngineCache
            .getInstance()
            .put(ENGINE_ID, flutterEngine)

        // Set up the MethodChannel here, after the FlutterEngine is initialized
        channel = MethodChannel(flutterEngine.dartExecutor, CHANNEL)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFlutterLaunchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flutterLaunchButton.setOnClickListener {
            startActivity(
                FlutterActivity
                    .withCachedEngine(ENGINE_ID)
                    .build(this.requireContext())
            )
        }

        // MethodChannel handler for receiving data from Flutter
        channel.setMethodCallHandler { call, result ->
            when(FlutterChannelAction.getAction(call.method)){
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
        // Destroy the cached Flutter engine
        FlutterEngineCache.getInstance().apply {
            get(ENGINE_ID)?.destroy()
        }
    }
}