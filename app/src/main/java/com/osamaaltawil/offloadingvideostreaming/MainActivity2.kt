package com.osamaaltawil.offloadingvideostreaming

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MainActivity2 : AppCompatActivity() {
    var url=""
    var player:SimpleExoPlayer? = null
    var playWhenReady=true
    var currentWindow=0
    var playbackPosition:Long=0
    var playerView:PlayerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
          playerView=findViewById(R.id.player_view)
        url=intent.getStringExtra("url").toString()
//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(video)
//        video.setVideoPath(url)
//        video.setMediaController(mediaController)
//        video.requestFocus()
//        video.start()
    }
    fun initPlayer(){
        player = SimpleExoPlayer.Builder(this).build()
        // Bind the player to the view.
        playerView!!.player = player
        player!!.playWhenReady = true
        player!!.seekTo(currentWindow,playbackPosition)
        player!!.setMediaSource(buildMediaSource(),false)
        player!!.prepare()
    }
    fun releasePlayer() {
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }

    }
    private fun buildMediaSource(): MediaSource {
        val userAgent =
                Util.getUserAgent(playerView!!.context, playerView!!.context.getString(R.string.app_name))

        val dataSourceFactory = DefaultDataSourceFactory(this,userAgent)

        val mediaSource =ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(Uri.parse(url)))

        return mediaSource
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT>=24){
            initPlayer()

        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player==null){
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }    }
}