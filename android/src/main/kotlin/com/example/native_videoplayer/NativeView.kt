

package com.example.videoplayer


import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaItem.LiveConfiguration
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import io.flutter.plugin.platform.PlatformView


internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) : PlatformView {


    private val  player : ExoPlayer;
    private val playerview : StyledPlayerView;

    override fun getView(): View {
        return playerview
    }

    override fun dispose() {
        playerview.player!!.pause();
        player!!.pause();
        player.release();
    }



    init {

        player = ExoPlayer.Builder(context).build()

        playerview = StyledPlayerView(context)
        playerview.setShowFastForwardButton(false)
        playerview.setShowNextButton(false)
        playerview.setShowFastForwardButton(false)
        playerview.setShowPreviousButton(false)
        playerview.setShowRewindButton(false)


        playerview.setPlayer(player);
//        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
//        val mediaSource: HlsMediaSource =
//            HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoURL))
//        player.setMediaSource(buildMediaSource());
        val mediaItem: MediaItem = MediaItem.Builder()
            .setUri(Uri.parse(creationParams!!.get("url").toString()))
            .setLiveConfiguration(
                LiveConfiguration.Builder()
                    .setMaxPlaybackSpeed(1.02f)
                    .build()
            )
            .build()
        player.setMediaItem(mediaItem);
        player.prepare();
        player.playWhenReady = true
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
//                        progressBar.setVisibility(View.GONE);
                    player.playWhenReady = true
                } else if (state == Player.STATE_BUFFERING) {
//                        progressBar.setVisibility(View.VISIBLE);

                } else {
//                        progressBar.setVisibility(View.GONE);
                    player.playWhenReady = true
                }
            }

            fun onPlayerError(error: ExoPlaybackException) {
                when (error.type) {
                    ExoPlaybackException.TYPE_SOURCE -> Log.e(
                        "error1",
                        "TYPE_SOURCE: " + error.sourceException.message
                    )

                    ExoPlaybackException.TYPE_RENDERER -> Log.e(
                        "error2",
                        "TYPE_RENDERER: " + error.rendererException.message
                    )

                    ExoPlaybackException.TYPE_UNEXPECTED -> Log.e(
                        "error3",
                        "TYPE_UNEXPECTED: " + error.unexpectedException.message
                    )
                }
            }
        })

    }





}
