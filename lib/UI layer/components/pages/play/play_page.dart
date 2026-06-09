import 'package:donotimagine/UI%20layer/viewmodels/play_change_notifier.dart';
import 'package:donotimagine/UI%20layer/viewmodels/viewport/viewport.dart';
import 'package:donotimagine/UI%20layer/viewmodels/global_view_modal.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class PlayPage extends AppPage {
  @override
  // TODO: implement main
  Widget get main {
    return DummyView();
  }

  @override
  // TODO: implement sidebar
  Widget get sidebar => Consumer2<PlayChangeNotifier, DummyViewport>(
    builder:
        (
          BuildContext context,
          PlayChangeNotifier playChangeNotifier,
          DummyViewport dummyViewport,
          Widget? child,
        ) {
          return PlayControls(
            onNext: dummyViewport.next,
            onPause: dummyViewport.onPause,
            onPrevious: dummyViewport.previous,
            onplay: () => {},
            progress: .5,
            isPlaying: true,
          );
        },
  );

  @override
  // TODO: implement title
  String get title => "Display";
}

class DummyView extends StatelessWidget {
  const DummyView({super.key});

  @override
  Widget build(BuildContext context) {
    DummyViewport dummyViewport = Provider.of<DummyViewport>(context);
    return Container(child: dummyViewport.setUpUI());
  }
}

class PlayControls extends StatelessWidget {
  final VoidCallback onPrevious;
  final VoidCallback onPause;
  final VoidCallback onplay;
  final VoidCallback onNext;
  final double progress;
  final bool isPlaying;
  const PlayControls({
    super.key,
    required this.onPrevious,
    required this.onPause,
    required this.onplay,
    required this.onNext,
    required this.progress,
    required this.isPlaying,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        LinearProgressIndicator(
          value: progress, // Replace with actual progress value
          backgroundColor: Theme.of(context).dividerColor,
          color: Theme.of(context).primaryColor,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            IconButton(icon: Icon(Icons.skip_previous), onPressed: onPrevious),
            SizedBox(width: 16),
            isPlaying
                ? IconButton(icon: Icon(Icons.pause), onPressed: onPause)
                : IconButton(icon: Icon(Icons.play_arrow), onPressed: onplay),
            SizedBox(width: 16),
            IconButton(icon: Icon(Icons.skip_next), onPressed: onNext),
          ],
        ),
      ],
    );
  }
}

abstract class ViewPort {
  void onNext();
  void onPrevious() {}
  void onPause() {}
  void onResume() {}
  double get progress => 0.5;
  bool get isPlaying => true;
}
