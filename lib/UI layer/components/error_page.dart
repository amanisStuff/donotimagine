import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:flutter/widgets.dart';

class ErrorPage extends AppPage {
  @override
  // TODO: implement main
  Widget get main => Center(
    child: Text("How did you get here? Anyways... this is a deadend "),
  );

  @override
  // TODO: implement sidebar
  Widget get sidebar => Center(child: Text("go back please!!"));

  @override
  // TODO: implement title
  String get title => "error:404";
}
