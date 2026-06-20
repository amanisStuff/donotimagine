import 'package:donotimagine/UI%20layer/viewmodels/play_change_notifier.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/dummy_viewport.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/viewport.dart';
import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:donotimagine/UI%20layer/viewmodels/theme_settings_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (context) => GlobalViewModal()),
        ChangeNotifierProvider(create: (context) => ThemeSettingsViewModel()),
        ChangeNotifierProvider(create: (context) => DummyViewport()),
        ChangeNotifierProvider(create: (context) => PlayChangeNotifier()),
        ChangeNotifierProvider(create: (context) => ImageViewport()),
        // ImageViewport,
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
    ThemeSettingsViewModel themeSettingsViewModel =
        Provider.of<ThemeSettingsViewModel>(context);
    return MaterialApp(
      title: 'Flutter Demo',
      themeMode: themeSettingsViewModel.themeMode,
      theme: ThemeData.from(
        colorScheme: ColorScheme.light(),
        useMaterial3: true,
      ),
      darkTheme: ThemeData.from(
        colorScheme: ColorScheme.dark(),
        textTheme: TextTheme(),
        useMaterial3: true,
      ),
      home: Scaffold(
        body: Row(
          children: [
            Expanded(child: Container(child: globalViewModal.page.main)),
            Container(
              width: 300,
              color: Theme.of(context).primaryColor,
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
