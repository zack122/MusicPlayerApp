package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateDataModel();
    }
    void populateDataModel(){
        //initialize properties of playlist
        playlist.name = "My Playlist";
        playlist.songs = new ArrayList<>();
        // create and initialize the first song
        Song song = new Song();
        song.songName = "Acoustic Breeze";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.acousticbreeze;
        song.mp3Resource = R.raw.acousticbreeze;
        //adding the first song to the array of songs in the playlist
        playlist.songs.add(song);
        song = new Song();
        song.songName = "A New Beginning";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.anewbeginning;
        song.mp3Resource = R.raw.anewbeginning;
        playlist.songs.add(song);
        song = new Song();
        song.songName = "Creative Minds";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.creativeminds;
        song.mp3Resource = R.raw.creativeminds;
        playlist.songs.add(song);
        song = new Song();
        song.songName = "Going Higher";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.goinghigher;
        song.mp3Resource = R.raw.goinghigher;
        playlist.songs.add(song);
        song = new Song();
        song.songName = "Happy Rock";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.happyrock;
        song.mp3Resource = R.raw.happyrock;
        playlist.songs.add(song);
        song = new Song();
        song.songName = "Hey";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.hey;
        song.mp3Resource = R.raw.hey;
        playlist.songs.add(song);
        song = new Song();
        song.songName = "Summer";
        song.artistName = "bensound.com";
        song.imageResource = R.drawable.summer;
        song.mp3Resource = R.raw.summer;
        playlist.songs.add(song);
    }
    //properties
    Playlist playlist = new Playlist();
}