package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class music_main extends AppCompatActivity {

    void onUserSelectedSongAtPosition(int position){
        switchSong(currentSongIndex, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);

        populateDataModel();

        connectXMLViews();
        setupRecyclerView();
        displayCurrentSong();

        setupButtonHandlers();

        configureNextButton();

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

    void connectXMLViews(){
        recyclerView = findViewById(R.id.song_list_view);
        imageView = findViewById(R.id.cover_image);
        songNameTextView = findViewById(R.id.song_name_textview);
        artistNameTextView = findViewById(R.id.artsit_name_textview);
        previousButton = findViewById(R.id.previous_button);
        pauseButton = findViewById(R.id.pause_button);
        playButton = findViewById(R.id.play_button);
        nextButton = findViewById(R.id.next_button);
    }

    void setupRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // connect adapter to recyclerView
        songAdapter = new SongAdapter(this, playlist.songs, this);
        recyclerView.setAdapter(songAdapter);
    }

    void displayCurrentSong() {
        Song currentSong = playlist.songs.get(currentSongIndex);
        imageView.setImageResource(currentSong.imageResource);
        songNameTextView.setText(currentSong.songName);
        artistNameTextView.setText(currentSong.artistName);
    }

    void setupButtonHandlers() {

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this will be called when previous button is tapped
                System.out.println("previous button tapped");
                if(currentSongIndex - 1 >= 0) {
                    switchSong(currentSongIndex, currentSongIndex - 1);
                }
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("next button was tapped");
                if (currentSongIndex + 1 < playlist.songs.size()) {
                    switchSong(currentSongIndex, currentSongIndex + 1);

                }
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("pause button was tapped");
                pauseCurrentSong();
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("play button was tapped");
                playCurrentSong();
            }
        });
    }

    void switchSong(int fromIndex, int toIndex){
        //tell song adapter to refresh currently selected song
        songAdapter.notifyItemChanged(currentSongIndex);

        //update current song index
        currentSongIndex = toIndex;
        displayCurrentSong();
        //Tell song adapter to refresh the newly selected song
        songAdapter.notifyItemChanged(currentSongIndex);

        //Scroll to make current song visible in recycler view
        recyclerView.scrollToPosition(currentSongIndex);

        //Check if a current song is playing
        if (mediaPlayer!= null && mediaPlayer.isPlaying()){
            //a song is playing, pause song
            pauseCurrentSong();

            //invalidate the media player
            mediaPlayer = null;
            //play the new current song
            playCurrentSong();
        }
        else{
            //a song is not currently playing, invalidate the media player

            mediaPlayer = null;
        }



    }
    void play() {

        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.play);
        }else{
            mediaPlayer.start();
            playButton.setImageResource(R.drawable.pause);
        }
    }

    void pauseCurrentSong() {

        System.out.println("pausing song at index" + currentSongIndex);
        // check if mediaPlayer already exist
        if(mediaPlayer != null){
            // media player exist go ahead and pause it
            mediaPlayer.pause();
        }
    }

    void playCurrentSong() {
        System.out.println("playing song at index" + currentSongIndex);
        //cHeck if media player already exist
        if(mediaPlayer == null){
            // mediaPlayer has not been created

            //get the song object corresponding to the current song
            Song currentSong = playlist.songs.get(currentSongIndex);

            //create media player for the mp3 resource of the current song
            mediaPlayer = MediaPlayer.create(music_main.this, currentSong.mp3Resource);

        }

        //play the song
        mediaPlayer.start();

    }

    //properties
    Playlist playlist = new Playlist();
    SongAdapter songAdapter;
    Integer currentSongIndex = 0;
    //MediaPlayer to MP3
    MediaPlayer mediaPlayer = null;
    //xml views
    RecyclerView recyclerView;
    ImageView imageView;
    TextView songNameTextView;
    TextView artistNameTextView;
    ImageButton previousButton;
    ImageButton pauseButton;
    ImageButton playButton;
    ImageButton nextButton;






    private void configureNextButton(){
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextpage);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(music_main.this, MainActivity.class));
            }
        });
    }
}