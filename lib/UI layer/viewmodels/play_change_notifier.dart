import 'dart:async';
import 'package:flutter/material.dart' hide Viewport;

class PlayChangeNotifier extends ChangeNotifier {
  Duration duration = Durations.medium1;
  Duration durationremaining = Durations.medium1;
  Timer? _timer;
  bool isPlaying = false;
  void restartTimer() {
    if (_timer != null) {
      _timer!.cancel();
    }
    startTimer();
  }

  double get progress => (_timer?.tick ?? 0.0) / duration.inSeconds.toDouble();

  void startTimer() {
    _timer = Timer.periodic(duration, (Timer timer) {
      durationremaining = duration - Duration(seconds: timer.tick);
      print("Remaining time: ${durationremaining.inSeconds} seconds");
      notifyListeners();
    });
  }

  void pauseTimer() {
    _timer?.cancel();
    _timer = null;
  }

  void resumeTimer() {}

  void stopTimer() {
    _timer?.cancel();
    _timer = null;
  }
}
