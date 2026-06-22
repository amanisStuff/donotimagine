import 'dart:typed_data';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/image_gellery.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/imagedisplay.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/viewport.dart';
import 'package:flutter/material.dart';

class ImageViewport extends ChangeNotifier implements AppViewport {
  List<Uint8List> imageDataList = [];
  int? _currentIndex;
  bool displayImage = false;
  ImageViewport() {
    imageDataList.isNotEmpty ? _currentIndex = 0 : _currentIndex = null;
  }
  int? get currentIndex => _currentIndex;
  @override
  void next() {
    if (_currentIndex != null) {
      _currentIndex = _currentIndex! + 1 % imageDataList.length;
    }
    notifyListeners();
  }

  @override
  void onPause() {
    // TODO: implement onPause
    if (_currentIndex != null) {
      _currentIndex = _currentIndex! + 1 % imageDataList.length;
    }
    displayImage = false;
    notifyListeners();
  }

  @override
  void onPlay() {
    // TODO: implement onPlay
    displayImage = true;
    notifyListeners();
  }

  @override
  void previous() {
    // TODO: implement previous
  }

  @override
  Widget setUpUI() {
    // TODO: implement setUpUI
    return displayImage
        ? Imagedisplay()
        : ImageGellery(selectedIndex: _currentIndex);
  }
}
