import 'package:donotimagine/UI%20layer/viewmodels/viewport/viewport.dart';
import 'package:flutter/material.dart';

class DummyViewport extends ChangeNotifier implements AppViewport {
  final List<IconData> _icons = [
    Icons.play_arrow,
    Icons.pause_circle,
    Icons.skip_next,
    Icons.skip_previous,
  ];

  int currentIndex = 0;

  @override
  Widget setUpUI() {
    return Icon(_icons[currentIndex]);
  }

  @override
  void onPlay() {
    // THIS MAY BE USELESS MAYBE REMOVE IT LATER
  }

  @override
  void onPause() {
    // THIS MAY BE USELESS MAYBE REMOVE IT LATER
  }

  @override
  void next() {
    currentIndex = (currentIndex + 1) % _icons.length;
    notifyListeners();
  }

  @override
  void previous() {
    currentIndex = (currentIndex - 1) % _icons.length;
    notifyListeners();
  }
}
