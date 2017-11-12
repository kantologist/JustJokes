package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.example.JokeClass;
import com.example.androidjokes.JokesActivity;
import com.example.user.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by user on 12/11/2017.
 */


public class EndpointsAsyncTask extends AsyncTask<Pair<Context, ProgressBar>, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private ProgressBar progressBar;

    @Override
    protected String doInBackground(Pair<Context, ProgressBar> ... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://cohesive-memory-145712.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        progressBar= params[0].second;


        try {
            return myApiService.myEndpoint().fetchJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, JokesActivity.class);
        JokeClass joke = new JokeClass();
        intent.putExtra("joke", joke.tellJoke());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }
}
