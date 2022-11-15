package com.example.musicapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val music = listOf(
        "Bob Marley",
        "Calvin Harris",
        "Coldplay",
        "David Guetta",
        "Gigi D'Agustino",
        "Katy Perry",
        "Linkin Park",
        "Muse",
        "Paktofonika",
        "Rihanna",
        "Skrillex",
        "The Beatles"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in music){
            createArtist(i)
        }
    }

    fun createArtist(artistName: String){
        val artist = layoutInflater.inflate(R.layout.artist, null)
        val image = artist.findViewById<ImageButton>(R.id.artist_image)
        image.setOnClickListener{
            showArtistInfo(artistName)
        }

        val artistNameView = artist.findViewById<TextView>(R.id.artist_name)
        val knowHimBox = artist.findViewById<CheckBox>( R.id.know_him_box)

        val artistName2 = artistName.toLowerCase().replace(" ","")
            .replace("'","")
        val imageID = resources.getIdentifier(artistName2,"drawable", packageName)
        image.setImageResource(imageID)
        artistNameView.text = artistName
        grid_of_artists.addView(artist)
    }

    fun showArtistInfo(artistName: String){
        val artistName2 = artistName.toLowerCase().replace(" ","")
            .replace("'","")
        val textFileID = resources.getIdentifier(artistName2,"raw",packageName)
        val fileText = resources.openRawResource(textFileID).bufferedReader().readText()

        val mp3FileID = resources.getIdentifier(artistName2 + "_music_example","raw",packageName)
        val mp = MediaPlayer.create(this, mp3FileID)
        mp.start()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info about $artistName")
        builder.setMessage(fileText)
        builder.setPositiveButton("OK"){ _, _ ->
            mp.stop()
        }
        val dialog = builder.create()
        dialog.show()
    }
}