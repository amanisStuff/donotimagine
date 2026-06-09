import 'package:flutter/material.dart';

abstract class Viewport {
  // Basic functionality to interact with the play page as a viewmodel
  Widget setUpUI();
  void onPlay() {
    // Handle the play button being pressed
  }

  void onPause() {
    // Handle the pause button being pressed
  }

  void next() {
    // Handle skipping to the next track
  }

  void previous() {
    // Handle skipping to the previous track
  }
}

class DummyViewport extends ChangeNotifier implements Viewport {
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
    // _currentIndex = (_currentIndex + 1) % _icons.length;
  }

  @override
  void onPause() {
    // _currentIndex = (_currentIndex + 1) % _icons.length;
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
