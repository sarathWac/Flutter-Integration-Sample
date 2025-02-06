import 'package:flutter/material.dart';

class ScreenWithReturnData extends StatefulWidget {
  const ScreenWithReturnData({super.key});

  @override
  State<ScreenWithReturnData> createState() => _ScreenWithReturnDataState();
}

class _ScreenWithReturnDataState extends State<ScreenWithReturnData> {
  final TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen That Returns Data')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              decoration: const InputDecoration(labelText: 'Enter some data'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context, _controller.text);
              },
              child: const Text('Return Data'),
            ),
          ],
        ),
      ),
    );
  }
}
