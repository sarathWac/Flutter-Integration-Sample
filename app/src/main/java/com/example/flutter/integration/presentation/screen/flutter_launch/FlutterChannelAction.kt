package com.example.flutter.integration.presentation.screen.flutter_launch


/**
 * Enum class representing the different actions that can be performed over a Flutter Method Channel.
 *
 * This enum defines the valid method names that can be used to communicate between the native
 * (Kotlin/Java) side and the Flutter side of an application via a Method Channel.
 *
 * Each enum entry corresponds to a specific action and holds the associated method name string.
 *
 * @property methodName The string representing the method name used to identify this action in
 *                      Method Channel communication.
 */
enum class FlutterChannelAction(private val methodName: String) {
    SEND_DATA("nativeToFlutter"),
    RECEIVE_DATA("flutterToNative"),
    NONE("none");
    companion object {

        /**
         * Retrieves the corresponding FlutterChannelAction for a given event string.
         *
         * This function searches through a collection of predefined actions (presumably `entries`)
         * to find an action whose `methodName` matches the provided `event`.
         *
         * @param event The string representing the event for which to find the corresponding action.
         * @return The FlutterChannelAction associated with the given event, or [NONE] if no matching action is found.
         *         [NONE] is likely a default or null-like action representing no operation.
         */
        fun getAction(event: String): FlutterChannelAction {
            return entries.find { it.methodName == event } ?: NONE
        }
    }
}