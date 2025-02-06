import 'package:flutter/material.dart';

class ScreenFour extends StatelessWidget {
  final String data;
  const ScreenFour({super.key, required this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen Four')),
      body: Center(
        child: Text(data, style: const TextStyle(fontSize: 20)),
      ),
    );
  }
}
