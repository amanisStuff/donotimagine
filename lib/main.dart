import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (context) => GlobalViewModal()),
      ],
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    GlobalViewModal globalViewModal = Provider.of<GlobalViewModal>(context);
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(colorScheme: .fromSeed(seedColor: Colors.deepPurple)),
      home: Scaffold(
        body: Row(
          children: [
            // main
            Expanded(
              child: Container(
                color: Colors.blueGrey,
                child: globalViewModal.page.main,
              ),
            ),
            // side
            Container(
              width: 300,
              color: Colors.lightBlueAccent,
              child: Column(
                children: [
                  Text(
                    globalViewModal.page.title,
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight(800)),
                  ),
                  Expanded(child: globalViewModal.page.sidebar),
                  NavigationButtons(globalViewModal: globalViewModal),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class NavigationButtons extends StatelessWidget {
  const NavigationButtons({super.key, required this.globalViewModal});

  final GlobalViewModal globalViewModal;

  @override
  Widget build(BuildContext context) {
    List<Widget> buttons = PageNames.values
        .where((page) => page != globalViewModal.currentPage)
        .map(
          (page) => IconButton(
            onPressed: () => {globalViewModal.currentPage = page},
            icon: Icon(
              pageIcons[page],
            ), // Use the icon property of each PageName
          ),
        )
        .toList();

    return Row(children: buttons);
  }
}
