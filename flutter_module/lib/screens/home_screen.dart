import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Home Screen')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/screen1'),
              child: const Text('Go to Screen 1'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/sendReceive'),
              child: const Text('Go to Send Receive'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/screen3'),
              child: const Text('Go to Screen 3'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/screen4'),
              child: const Text('Go to Screen 4'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/nestedScreen1'),
              child: const Text('Go to Nested Screen 1'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/argumentScreen', arguments: "Hello, this is a passed argument!"),
              child: const Text('Go to Screen with Arguments'),
            ),
            ElevatedButton(
              onPressed: () async {
                final result = await Navigator.pushNamed(context, '/returnScreen');
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(content: Text('Returned: $result')),
                );
              },
              child: const Text('Go to Screen That Returns Data'),
            )
          ],
        ),
      ),
    );
  }
}
