package com.example.bookclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bookclub.repository.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository.init(getApplication());
    }
}