import 'package:flutter/material.dart';

class ScreenThree extends StatelessWidget {
  final String data;
  const ScreenThree({super.key, required this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen Three')),
      body: Center(
        child: Text(data, style: const TextStyle(fontSize: 20)),
      ),
    );
  }
}
