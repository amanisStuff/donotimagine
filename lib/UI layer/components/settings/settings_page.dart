import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:donotimagine/UI%20layer/viewmodels/theme_settings_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class SettingsPage implements AppPage {
  @override
  Widget get main => ThemeSettingsPage();

  @override
  Widget get sidebar => ListView(
    children: [
      ListTile(
        leading: Icon(Icons.settings),
        title: Text("General"),
        onTap: () {
          // Navigate to General settings page
        },
      ),
      ListTile(
        leading: Icon(Icons.palette),
        title: Text("Appearance"),
        onTap: () {
          // Navigate to Appearance settings page
        },
      ),
      ListTile(
        leading: Icon(Icons.lock),
        title: Text("Security"),
        onTap: () {
          // Navigate to Security settings page
        },
      ),
      ListTile(
        leading: Icon(Icons.privacy_tip),
        title: Text("Privacy"),
        onTap: () {
          // Navigate to Privacy settings page
        },
      ),
    ],
  );

  @override
  String get title => "settings page";
}

class ThemeSettingsPage extends StatelessWidget {
  const ThemeSettingsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Consumer<ThemeSettingsViewModel>(
      builder:
          (BuildContext context, ThemeSettingsViewModel value, Widget? child) {
            return Padding(
              padding: const EdgeInsets.all(8.0),
              child: ListView(
                children: [
                  SettingsCellToggle(
                    onPressed: value.toggleTheme,
                    value: value.themeMode == ThemeMode.light,
                  ),
                  SettingsCellDouble(
                    text: "font size",
                    value: value.fontSize,
                    onChanged: (fontsize) => value.set(fontSize: fontsize),
                  ),
                ],
              ),
            );
          },
    );
  }
}

class SettingsCellToggle extends StatelessWidget {
  final VoidCallback? onPressed;
  final bool value;
  final String text;
  const SettingsCellToggle({
    super.key,
    this.onPressed,
    this.value = false,
    this.text = "toggle",
  });
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(text),
          Switch(onChanged: (bool value) => onPressed?.call(), value: value),
        ],
      ),
    );
  }
}

class SettingsCellDouble extends StatelessWidget {
  final String text;
  final double value;
  final Function(double)? onChanged;
  const SettingsCellDouble({
    super.key,
    required this.text,
    required this.value,
    this.onChanged,
  });
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(text),
          // a value slider
          Expanded(child: SizedBox.shrink()),
          Text(value.toStringAsFixed(0)),
          Slider(
            value: value,
            onChanged: onChanged,
            min: 12,
            max: 18,
            divisions: 6,
          ),
        ],
      ),
    );
  }
}

class SettingsCellString extends StatelessWidget {
  final String text;
  final String value;
  final Function(String)? onChanged;
  const SettingsCellString({
    super.key,
    required this.text,
    required this.value,
    this.onChanged,
  });
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(text),
          TextField(
            controller: TextEditingController(text: value),
            onChanged: onChanged,
            decoration: InputDecoration(border: InputBorder.none),
          ),
        ],
      ),
    );
  }
}
