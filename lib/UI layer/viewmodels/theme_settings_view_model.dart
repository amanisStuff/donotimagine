import 'package:donotimagine/data%20layer/models/theme_settings_model.dart';
import 'package:donotimagine/data%20layer/services/settings_service.dart';
import 'package:flutter/material.dart';

class ThemeSettingsViewModel extends ChangeNotifier with ThemeSettingsModel {
  SettingsService settingsService = SettingsService();
  ThemeSettingsViewModel._internal();
  static final ThemeSettingsViewModel _instance =
      ThemeSettingsViewModel._internal();
  factory ThemeSettingsViewModel() => _instance;
  @override
  bool set({int? fontSize, ThemeMode? themeMode}) {
    if (super.set(fontSize: fontSize, themeMode: themeMode)) {
      settingsService.saveThemeSettings();
      notifyListeners();
      return true;
    }
    return false;
  }

  void toggleTheme() {
    ThemeMode newTheme = themeMode == ThemeMode.dark
        ? ThemeMode.light
        : ThemeMode.dark;
    print("original: $themeMode | new: $newTheme");
    set(themeMode: newTheme);
  }
}
