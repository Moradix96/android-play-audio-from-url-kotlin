package ir.samiantec.playaudiofromurl

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

/* Implemented Based on java source
   in https://www.geeksforgeeks.org/how-to-play-audio-from-url-in-android/ */

class MainActivity : AppCompatActivity() {

    lateinit var playBtn: Button
    lateinit var pauseBtn: Button
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playBtn = findViewById(R.id.idBtnPlay)
        pauseBtn = findViewById(R.id.idBtnPause)

        playBtn.setOnClickListener {
            playAudio();
        }

        pauseBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                Toast.makeText(this@MainActivity, "Audio has been paused", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity, "Audio has not played", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun playAudio() {
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }
}