package com.example.songsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.songsearch.model.Util;
import com.example.songsearch.model.SongAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchSong;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is = getResources().openRawResource(R.raw.songs);
        Util.setIo(is);
        SongAdapter adapter = null;
        try {
            //SongAdapter adapter = new SongAdapter(Arrays.asList("adsf","ghjfgh","dsfsdf","dsfg","dfgdfg","dsfdsfasdfasdfadsfasd"));
            adapter = new SongAdapter(Util.getSongList());
            recyclerView = findViewById(R.id.recyclerSong);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchSong = findViewById(R.id.searchSong);
        SongAdapter finalAdapter = adapter;
        searchSong.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                //TODO
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                finalAdapter.filter(s);
                return true;
            }
        });
    }

}