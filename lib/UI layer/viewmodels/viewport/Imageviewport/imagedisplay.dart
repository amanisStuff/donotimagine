import 'package:flutter/material.dart';

class Imagedisplay extends StatelessWidget {
  const Imagedisplay({super.key});

  @override
  Widget build(BuildContext context) {
    return Image.network(
      "https://64.media.tumblr.com/9ef5ecc820d8339eccf9c464da03478d/0ad2a6e9210cc2de-fa/s2048x3072/7d92f7045a35bc2c328b60e4d5bc39c9d193f9ce.pnj",
    );
  }
}
