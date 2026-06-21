import 'dart:io';
import 'dart:typed_data';

import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/image_gellery.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/Imageviewport/imagedisplay.dart';
import 'package:flutter/material.dart';

abstract class AppViewport {
  Widget setUpUI();
  void onPlay();
  void onPause();
  void next();
  void previous();
}
