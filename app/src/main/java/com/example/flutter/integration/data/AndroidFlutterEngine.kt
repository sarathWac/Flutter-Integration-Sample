package com.example.flutter.integration.data

import android.content.Context
import android.util.Log
import dagger.hilt.android.scopes.FragmentScoped
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

@FragmentScoped
class AndroidFlutterEngine private constructor(
    private val context: Context,
    val engineId: String
) {

    companion object {
        const val DEFAULT_ENGINE_ID = "sample_flutter_engine_id"
    }

    private val flutterEngine by lazy {
        FlutterEngine(context).apply {
            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        }
    }

    init {
        FlutterEngineCache
            .getInstance()
            .put(engineId, flutterEngine)
        Log.d("AndroidFlutterEngine", "Flutter engine initialized")
        //channel = MethodChannel(flutterEngine.dartExecutor, channelId)
    }

    class Builder {

        private lateinit var context: Context
        private lateinit var engineId: String


        fun setContext(context: Context) = apply { this.context = context }
        fun setEngineId(engineId: String) = apply { this.engineId = engineId }

        fun build(): AndroidFlutterEngine {
            require(::context.isInitialized) { "Context must be set before building" }
            require(::engineId.isInitialized) { "Engine ID must be set before building" }

            return AndroidFlutterEngine(
                context = context,
                engineId = engineId
            )
        }
    }


    fun getChannel(id: String =  "com.example.app/channel"): MethodChannel {
        return MethodChannel(flutterEngine.dartExecutor, id)
    }

    fun cleanup() {
        // Destroy the cached Flutter engine
        FlutterEngineCache.getInstance().apply {
            get(engineId)?.destroy()
        }
    }

}