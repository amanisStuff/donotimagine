import 'package:donotimagine/UI%20layer/components/error_page.dart';
import 'package:flutter/material.dart';

enum PageNames { settings, main, error }

class GlobalViewModal extends ChangeNotifier {
  Map<PageNames, AppPage> pages = {PageNames.settings: SettingsPage()};
  PageNames _currentPage = PageNames.error;
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

class SettingsPage implements AppPage {
  @override
  Widget main = Center(child: Text("this is the settings page "));

  @override
  Widget sidebar = Center(child: Text("this is the settings sidebar"));

  @override
  // TODO: implement title
  String get title => "settings";
}
