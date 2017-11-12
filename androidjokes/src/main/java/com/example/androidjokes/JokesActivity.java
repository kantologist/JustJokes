package com.example.androidjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        TextView jokeView = (TextView) findViewById(R.id.joke_view);

        Intent intent = getIntent();

        String joke = intent.getStringExtra("joke");

        if(joke != null){
            jokeView.setText(joke);
        }
    }
}
