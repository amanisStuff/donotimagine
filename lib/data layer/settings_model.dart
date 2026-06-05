import 'package:flutter/material.dart';

abstract class SettingModel {
  void set();
}

enum SettingSection { theme, gallery, object3D }

class ThemeSettings extends ChangeNotifier implements SettingModel {
  int _fontSize = 16;
  ThemeMode _themeMode = ThemeMode.dark;
  ThemeSettings._internal();
  static final ThemeSettings _instance = ThemeSettings._internal();
  factory ThemeSettings() => _instance;
  int get fontSize => _fontSize;
  ThemeMode get themeMode => _themeMode;
  @override
  void set({int? fontSize, ThemeMode? themeMode}) {
    if (fontSize != null) {
      _fontSize = fontSize;
      notifyListeners();
    }
    if (themeMode != null) {
      _themeMode = themeMode;
      notifyListeners();
    }
  }
}
