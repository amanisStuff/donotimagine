import 'package:donotimagine/data%20layer/models/theme_settings_model.dart';
import 'package:flutter/material.dart';

class ThemeSettingsViewModel extends ChangeNotifier with ThemeSettingsModel {
  ThemeSettingsViewModel._internal();
  static final ThemeSettingsViewModel _instance =
      ThemeSettingsViewModel._internal();
  factory ThemeSettingsViewModel() => _instance;
  // @override
  // int get fontSize => super.fontSize;
  // @override
  // ThemeMode get themeMode => super.themeMode;
  // make it different later on
  @override
  bool set({int? fontSize, ThemeMode? themeMode}) {
    if (super.set(fontSize: fontSize, themeMode: themeMode)) {
      notifyListeners();
      return true;
    }
    return false;
  }
}
