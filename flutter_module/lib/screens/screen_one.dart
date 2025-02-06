import 'package:flutter/material.dart';

class ScreenOne extends StatelessWidget {
  final String data;
  const ScreenOne({super.key, required this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen One')),
      body: Center(
        child: Text(data, style: const TextStyle(fontSize: 20)),
      ),
    );
  }
}
