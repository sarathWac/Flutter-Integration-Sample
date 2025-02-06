import 'package:flutter/material.dart';

class ScreenTwo extends StatelessWidget {
  final String data;
  const ScreenTwo({super.key, required this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen Two')),
      body: Center(
        child: Text(data, style: const TextStyle(fontSize: 20)),
      ),
    );
  }
}
