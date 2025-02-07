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

//The unique identifier for the Flutter engine instance. Used to cache engine and retrieve it.
private const val ENGINE_ID = "sample_flutter_engine_id"

//The name of the Method Channel used for communication between Flutter and Android.
private const val CHANNEL = "com.example.app/channel"


class FlutterLaunchFragment : Fragment() {

    private var _binding: FragmentFlutterLaunchBinding? = null
    private val binding get() = _binding!!
    private lateinit var channel: MethodChannel


    /**
     * The FlutterEngine instance that manages the Flutter runtime.
     *
     *  - **Initial Navigation Route:** The `/sendReceive` route is set as the initial route. This means
     *    that when Flutter starts, it will navigate to this route by default.
     *  - **Default Dart Entrypoint:** The default Dart entrypoint (main()) is executed. This starts
     *    the execution of your Flutter application code.
     *
     *  The `FlutterEngine` is responsible for:
     *    - Executing Dart code.
     *    - Rendering Flutter UI.
     *    - Handling platform channels.
     *    - Managing the Flutter lifecycle.
     *
     * @see FlutterEngine
     * @see io.flutter.plugin.common.MethodChannel
     * @see io.flutter.plugin.common.EventChannel
     * @see DartExecutor.DartEntrypoint
     */
    private val flutterEngine: FlutterEngine by lazy {
        FlutterEngine(requireContext()).apply {
            // Set the initial route to navigate to when Flutter starts (optional)
            navigationChannel.setInitialRoute("/sendReceive")
            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        }
    }

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        /**
         * Cache the FlutterEngine to be used by FlutterActivity.
         *
         * Could be created only when we are sure the user going to navigate to module
         */
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

        /**
         * Set up a MethodChannel handler.
         * This handler is responsible for handling method calls from Flutter.
         *
         * Here we can send/receive data from flutter
         */
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