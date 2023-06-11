import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'native_videoplayer_platform_interface.dart';

/// An implementation of [NativeVideoplayerPlatform] that uses method channels.
class MethodChannelNativeVideoplayer extends NativeVideoplayerPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('native_videoplayer');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
