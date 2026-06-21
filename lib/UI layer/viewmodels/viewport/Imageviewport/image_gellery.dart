import 'package:flutter/material.dart';

class ImageGellery extends StatelessWidget {
  const ImageGellery({
    super.key,
    this.imagePaths = const [],
    this.onAddImageClick,
    this.onImageClick,
    this.selectedIndex = 2,
  });
  final List<String> imagePaths;
  final GestureTapCallback? onAddImageClick;
  final Function(int)? onImageClick;
  final int? selectedIndex;
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
          imagePaths.length + 1,
          (index) => Container(
            decoration: BoxDecoration(
              color: selectedIndex == index ? Colors.red : Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(15)),
            ),
            child: Padding(
              padding: const EdgeInsets.all(2.0),
              child: Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(15)),
                ),
                clipBehavior: Clip.antiAlias,
                child: index == imagePaths.length
                    ? InkWell(
                        onTap: onAddImageClick,
                        child: MouseRegion(
                          cursor: SystemMouseCursors.click,
                          child: Container(
                            color: Colors.amber,
                            child: Icon(Icons.image_search, size: 32),
                          ),
                        ),
                      )
                    : InkWell(
                        onTap: () {
                          onImageClick == null
                              ? "do nothing"
                              : onImageClick!(index);
                        },
                        child: MouseRegion(
                          cursor: SystemMouseCursors.click,
                          child:
                              Image.network(
                                    imagePaths[index],
                                    fit: BoxFit.cover,
                                  )
                                  as Widget,
                        ),
                      ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
