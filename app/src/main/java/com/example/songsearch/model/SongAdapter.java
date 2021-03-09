package com.example.songsearch.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsearch.R;

import java.util.List;
import java.util.stream.Collectors;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    List<String> songs;
    List<String> songsCopy;

    public SongAdapter(List<String> songs) {
        this.songs = songs;
        this.songsCopy = songs;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        String[] parts = songs.get(position).split("-");
        if(parts.length > 1) {
            holder.songText.setText(parts[0]);
            holder.artistText.setText(parts[1]);
        }else {
            holder.songText.setText(parts[0]);
            holder.artistText.setText(parts[0]);
            System.out.println(parts[0]);
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void filter(String word) {
        String re = Util.toRegExpWithLowerAndUpper(word);
        songs = songsCopy.stream().filter(s -> s.matches(re)).collect(Collectors.toList());
        notifyDataSetChanged();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {

        TextView songText;
        TextView artistText;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);

            songText = itemView.findViewById(R.id.songText);
            artistText = itemView.findViewById(R.id.artistText);
        }

    }
}
