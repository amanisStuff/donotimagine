import 'dart:typed_data';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/image_gellery.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/imagedisplay.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/viewport.dart';
import 'package:flutter/material.dart';

class ImageViewport extends ChangeNotifier implements AppViewport {
  List<Uint8List> imageData = [];
  int currentIndex = 0;

  @override
  void next() {
    // TODO: implement next
  }

  @override
  void onPause() {
    // TODO: implement onPause
  }

  @override
  void onPlay() {
    // TODO: implement onPlay
  }

  @override
  void previous() {
    // TODO: implement previous
  }

  @override
  Widget setUpUI() {
    bool displayImage = true;
    // TODO: implement setUpUI
    return displayImage ? Imagedisplay() : ImageGellery();
  }
}
