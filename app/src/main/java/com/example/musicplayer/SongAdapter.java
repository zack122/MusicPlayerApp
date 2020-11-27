package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder> {
    //constructor
    SongAdapter(@NonNull Context context, @NonNull ArrayList<Song> songs, @NonNull MainActivity mainActivity){
        this.context = context;
        this.songs = songs;
        this.mainActivity = mainActivity;

    }

    // overridden methods
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This method is called whenever I need to create a new ViewHolder
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_song, parent, false);
        SongViewHolder viewHolder = new SongViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        //this method is called whenever an existing ViewHolder needs to be re-used
        //At this point we need to re-populate the ViewHolder
        Song song = songs.get(position);
        holder.imageView.setImageResource(song.imageResource);
        holder.songNameTextView.setText(song.songName) ;
        holder.artistNameTextView.setText(song.artistName);

        //Set the selected background to be visible/invisible
        if(position == mainActivity.currentSongIndex) {
            //current song index is at this position
            holder.selectedBackgroundView.setVisibility(View.VISIBLE);
        }
        else{
            //current song index is not at this position
            holder.selectedBackgroundView.setVisibility(View.INVISIBLE);
        }

        //Add button tap handler for this song
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("User tapped on song at index" + position);
                mainActivity.onUserSelectedSongAtPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //called whenever RecyclerView needs to how many items in total to display
        return songs.size();
    }

    //Properties
    Context context;
    ArrayList<Song> songs;
    MainActivity mainActivity;
}
