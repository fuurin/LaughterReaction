package com.komatsu.laughterreaction

import android.media.AudioAttributes
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sounds = listOf(
        R.raw.laugh_studio,
        R.raw.laugh_studio_woman,
        R.raw.laugh_studio_crap,
        R.raw.girl_laugh_short,
        R.raw.girl_laugh_long,
        R.raw.girl_surprising,
        R.raw.cheer,
        R.raw.cheer_woman,
        R.raw.surprising_studio
    )

    private val soundIds = mutableListOf<Int>()

    private lateinit var soundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(9)
            .build()

        sounds.forEach {
            "Load: $it".log()
            soundIds.add(soundPool.load(this, it, 1))
        }

        var counter = 0
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            "Loaded sampleID: $sampleId, status: $status".log()
            if(++counter == sounds.size) attachEvents()
        }
    }

    private fun attachEvents() {
        button1.setOnClickListener { play(0) }
        button2.setOnClickListener { play(1) }
        button3.setOnClickListener { play(2) }
        button4.setOnClickListener { play(3) }
        button5.setOnClickListener { play(4) }
        button6.setOnClickListener { play(5) }
        button7.setOnClickListener { play(6) }
        button8.setOnClickListener { play(7) }
        button9.setOnClickListener { play(8) }
    }

    private fun play(soundNum: Int) {
        soundPool.play(soundIds[soundNum], 1.0f, 1.0f, 0, 0, 1.0f)
    }
}
