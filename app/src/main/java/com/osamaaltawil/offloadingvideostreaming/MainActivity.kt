package com.osamaaltawil.offloadingvideostreaming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        video1.setOnClickListener {
            startActivity("https://firebasestorage.googleapis.com/v0/b/savecontactfirestore.appspot.com/o/file_example_MP4_480_1_5MG.mp4?alt=media&token=4a3fd3df-c085-417d-bfc5-178be67e774f")
        }
        video2.setOnClickListener {
            startActivity("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")

        }
        video3.setOnClickListener {
            startActivity("https://samplelib.com/lib/preview/mp4/sample-20s.mp4")
        }
        video4.setOnClickListener {
            startActivity("https://samplelib.com/lib/preview/mp4/sample-15s.mp4")

        }

    }

    fun startActivity(url: String) {
        val i = Intent(this, MainActivity2::class.java)
        i.putExtra("url", url)
        startActivity(i)
    }
}