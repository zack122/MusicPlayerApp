package com.example.musicplayer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class SongViewHolder extends RecyclerView.ViewHolder {

    //constructor
    public SongViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        imageView = itemView.findViewById(R.id.image_view);
        songNameTextView = itemView.findViewById(R.id.song_name_textview);
        artistNameTextView = itemView.findViewById(R.id.artsit_name_textview);
    }

    //properties
    View itemView;
    ImageView imageView;
    TextView songNameTextView;
    TextView artistNameTextView;

}
