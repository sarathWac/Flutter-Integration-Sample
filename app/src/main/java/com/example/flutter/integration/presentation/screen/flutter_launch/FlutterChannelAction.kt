package com.example.flutter.integration.presentation.screen.flutter_launch

enum class FlutterChannelAction(private val methodName: String) {
    SEND_DATA("nativeToFlutter"),
    RECEIVE_DATA("flutterToNative"),
    NONE("none");
    companion object {
        fun getAction(event: String): FlutterChannelAction {
            return entries.find { it.methodName == event } ?: NONE
        }
    }
}