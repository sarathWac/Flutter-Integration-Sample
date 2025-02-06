import 'package:flutter/material.dart';
import 'screens/home_screen.dart'; // Ensure this file exists
import 'screens/screen_one.dart';  // Same for other screens
import 'screens/screen_two.dart';
import 'screens/screen_three.dart';
import 'screens/screen_four.dart';
import 'screens/screen_with_arguments.dart';
import 'screens/screen_with_return_data.dart';
import 'screens/nested/nested_screen_one.dart';
import 'screens/nested/nested_screen_two.dart';
import 'screens/send_receive.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Navigation Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      initialRoute: '/',
      routes: {
        '/': (context) => const HomeScreen(),
        '/sendReceive': (context) => const SendReceiveScreen(),
        '/screen1': (context) => const ScreenOne(data: "Data for Screen 1"),
        '/screen2': (context) => const ScreenTwo(data: "Data for Screen 2"),
        '/screen3': (context) => const ScreenThree(data: "Data for Screen 3"),
        '/screen4': (context) => const ScreenFour(data: "Data for Screen 4"),
        '/nestedScreen1': (context) => const NestedScreenOne(),
        '/nestedScreen2': (context) => const NestedScreenTwo(),
        '/argumentScreen': (context) => const ScreenWithArguments(),
        '/returnScreen': (context) => const ScreenWithReturnData(),

      },
    );
  }
}
