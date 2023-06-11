import 'package:flutter_test/flutter_test.dart';
import 'package:native_videoplayer/native_videoplayer.dart';
import 'package:native_videoplayer/native_videoplayer_platform_interface.dart';
import 'package:native_videoplayer/native_videoplayer_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockNativeVideoplayerPlatform
    with MockPlatformInterfaceMixin
    implements NativeVideoplayerPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final NativeVideoplayerPlatform initialPlatform = NativeVideoplayerPlatform.instance;

  test('$MethodChannelNativeVideoplayer is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelNativeVideoplayer>());
  });

  test('getPlatformVersion', () async {
    NativeVideoplayer nativeVideoplayerPlugin = NativeVideoplayer();
    MockNativeVideoplayerPlatform fakePlatform = MockNativeVideoplayerPlatform();
    NativeVideoplayerPlatform.instance = fakePlatform;

    expect(await nativeVideoplayerPlugin.getPlatformVersion(), '42');
  });
}
