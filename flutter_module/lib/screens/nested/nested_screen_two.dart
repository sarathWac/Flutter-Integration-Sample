import 'package:flutter/material.dart';

class NestedScreenTwo extends StatelessWidget {
  const NestedScreenTwo({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Nested Screen 2')),
      body: const Center(
        child: Text('This is Nested Screen 2'),
      ),
    );
  }
}
