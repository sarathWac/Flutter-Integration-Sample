import 'package:flutter/material.dart';

class NestedScreenOne extends StatelessWidget {
  const NestedScreenOne({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Nested Screen 1')),
      body: Center(
        child: ElevatedButton(
          onPressed: () => Navigator.pushNamed(context, '/nestedScreen2'),
          child: const Text('Go to Nested Screen 2'),
        ),
      ),
    );
  }
}
