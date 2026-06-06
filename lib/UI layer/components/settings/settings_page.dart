import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:donotimagine/UI%20layer/viewmodels/theme_settings_view_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:provider/provider.dart';

class SettingsPage implements AppPage {
  @override
  // TODO: implement main
  Widget get main => Consumer<ThemeSettingsViewModel>(
    builder:
        (BuildContext context, ThemeSettingsViewModel value, Widget? child) {
          return Center(
            child: TextButton(
              onPressed: () => value.toggleTheme(),
              child: Text("toggle theme"),
            ),
          );
        },
  );

  @override
  // TODO: implement sidebar
  Widget get sidebar => Center(child: Text("settings "));

  @override
  // TODO: implement title
  String get title => "settings";
}
