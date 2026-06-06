import 'package:donotimagine/data%20layer/models/settings_model.dart';
import 'package:flutter/material.dart';

mixin class ThemeSettingsModel implements SettingModel {
  int _fontSize = 16;
  ThemeMode _themeMode = ThemeMode.dark;
  int get fontSize => _fontSize;
  ThemeMode get themeMode => _themeMode;
  static ThemeSettingsModel? _instance;
  factory ThemeSettingsModel() {
    _instance ??= ThemeSettingsModel._internal();
    return _instance!;
  }
  ThemeSettingsModel._internal();
  @override
  bool set({int? fontSize, ThemeMode? themeMode}) {
    bool aValueWasModified = false;
    if (fontSize != null) {
      _fontSize = fontSize;
      aValueWasModified = true;
    }
    if (themeMode != null) {
      _themeMode = themeMode;
      aValueWasModified = true;
    }
    return aValueWasModified;
  }
}
