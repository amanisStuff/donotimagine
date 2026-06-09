import 'package:donotimagine/UI%20layer/components/error_page.dart';
import 'package:donotimagine/UI%20layer/components/pages/play/play_page.dart';
import 'package:donotimagine/UI%20layer/components/pages/settings/settings_page.dart';
import 'package:flutter/material.dart';

enum PageNames { settings, main, error }

Map<PageNames, IconData> pageIcons = {
  PageNames.settings: Icons.settings,
  PageNames.error: Icons.error,
  PageNames.main: Icons.home,
};

class GlobalViewModal extends ChangeNotifier {
  Map<PageNames, AppPage> pages = {
    PageNames.settings: SettingsPage(),
    PageNames.main: PlayPage(),
  };
  PageNames _currentPage = PageNames.error;

  PageNames get currentPage => _currentPage;
  AppPage get page {
    return pages[_currentPage] ?? ErrorPage();
  }

  set currentPage(PageNames nextPage) {
    _currentPage = nextPage;
    notifyListeners();
  }
}

abstract class AppPage {
  Widget get sidebar;
  Widget get main;
  String get title;
}
