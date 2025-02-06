import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class SendReceiveScreen extends StatefulWidget {
  const SendReceiveScreen({super.key});

  @override
  _SendReceiveScreenState createState() => _SendReceiveScreenState();
}

class _SendReceiveScreenState extends State<SendReceiveScreen> {
  static const platform = MethodChannel('com.example.app/channel'); // Channel name

  // Variable to hold the data from the native side
  String receivedData = "Loading...";

  @override
  void initState() {
    super.initState();
    // Automatically fetch data from native side when the screen comes into view
    nativeToFlutter();
  }

  // Send data to native side
  Future<void> flutterToNative(BuildContext context) async {
    try {
      final String response = await platform.invokeMethod('flutterToNative', "Hello from Flutter!");
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Received from Flutter: $response')),
      );
    } on PlatformException catch (e) {
      print("Failed to send data to native: ${e.message}");
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Failed to send data: ${e.message}')),
      );
    }
  }

  // Receive data from native side (this happens automatically when the screen is displayed)
  Future<void> nativeToFlutter() async {
    try {
      final String result = await platform.invokeMethod('nativeToFlutter');
      setState(() {
        receivedData = result; // Update the state with received data
      });
    } on PlatformException catch (e) {
      setState(() {
        receivedData = "Failed to receive data: ${e.message}"; // Handle error
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Send and Receive Data')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Existing original layout elements
            ElevatedButton(
              onPressed: () => flutterToNative(context),
              child: const Text('Send Data to Native'),
            ),
            const SizedBox(height: 20), // Add spacing between buttons and data display
            ElevatedButton(
              onPressed: () => nativeToFlutter(),
              child: const Text('Receive Data from Native'),
            ),
            const SizedBox(height: 40), // Add spacing before showing received data
            // New section to show received data from Native
            Text(
              'Received from Native: $receivedData',
              style: TextStyle(fontSize: 18),
            ),
          ],
        ),
      ),
    );
  }
}
