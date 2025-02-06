import 'package:flutter/material.dart';

class ScreenWithArguments extends StatelessWidget {
  const ScreenWithArguments({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = ModalRoute.of(context)?.settings.arguments as String? ?? "No Arguments Passed";

    return Scaffold(
      appBar: AppBar(title: const Text('Screen With Arguments')),
      body: Center(
        child: Text(arguments, style: const TextStyle(fontSize: 20)),
      ),
    );
  }
}
