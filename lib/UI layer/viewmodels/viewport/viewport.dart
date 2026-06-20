import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/material.dart';

abstract class AppViewport {
  Widget setUpUI();
  void onPlay();
  void onPause();
  void next();
  void previous();
}

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
    // TODO: implement setUpUI
    return gellery();
  }
}

class gellery extends StatelessWidget {
  const gellery({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: GridView(
        gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
          maxCrossAxisExtent: 150,
          crossAxisSpacing: 8,
          mainAxisSpacing: 8,
        ),
        children: List.generate(
          15,
          (index) => Container(
            decoration: BoxDecoration(
              borderRadius: BorderRadius.all(Radius.circular(15)),
            ),
            clipBehavior: Clip.antiAlias,
            child: index == 14
                ? InkWell(
                    onTap: () {
                      print('Container Clicked via InkWell!');
                    },
                    child: MouseRegion(
                      cursor: SystemMouseCursors.click,
                      child: Container(
                        color: Colors.amber,
                        child: Icon(Icons.image_search, size: 32),
                      ),
                    ),
                  )
                : Image.network("https://picsum.photos/200") as Widget,
          ),
        ),
      ),
    );
  }
}
