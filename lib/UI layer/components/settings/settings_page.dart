import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';

class SettingsPage implements AppPage {
  @override
  // TODO: implement main
  Widget get main => Center(child: Text("options"));

  @override
  // TODO: implement sidebar
  Widget get sidebar => Center(child: Text("settings "));

  @override
  // TODO: implement title
  String get title => "settings";
}
