import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'native_videoplayer_method_channel.dart';

abstract class NativeVideoplayerPlatform extends PlatformInterface {
  /// Constructs a NativeVideoplayerPlatform.
  NativeVideoplayerPlatform() : super(token: _token);

  static final Object _token = Object();

  static NativeVideoplayerPlatform _instance = MethodChannelNativeVideoplayer();

  /// The default instance of [NativeVideoplayerPlatform] to use.
  ///
  /// Defaults to [MethodChannelNativeVideoplayer].
  static NativeVideoplayerPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [NativeVideoplayerPlatform] when
  /// they register themselves.
  static set instance(NativeVideoplayerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
