import 'package:donotimagine/data%20layer/models/settings/theme_settings_model.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SettingsService with ThemeSettingsMixin {}

mixin ThemeSettingsMixin {
  Future<void> saveThemeSettings() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool(
      'theme_setting_dark',
      ThemeSettingsModel().themeMode == ThemeMode.dark,
    );
    await prefs.setDouble('theme_setting_dark', ThemeSettingsModel().fontSize);
  }

  Future<void> loadThemeSettings() async {
    final prefs = await SharedPreferences.getInstance();
    final isDarkTheme = prefs.getBool('theme_setting_dark') ?? false;
    final fontSize = prefs.getDouble('theme_setting_dark') ?? 16;
    ThemeSettingsModel().set(
      fontSize: fontSize,
      themeMode: isDarkTheme ? ThemeMode.dark : ThemeMode.light,
    );
  }
}
