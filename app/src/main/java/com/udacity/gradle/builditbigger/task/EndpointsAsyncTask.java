package com.udacity.gradle.builditbigger.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.example.android.androidlibjoke.ShowJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static com.google.api.client.extensions.android.http.AndroidHttp.*;

/**
 * Created by vinicius.rocha on 2/16/18.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, ShowJokeActivity.class );
        intent.putExtra(ShowJokeActivity.TAG, result);
        context.startActivity(intent);
    }

    @Override
    protected String doInBackground(Context... contexts) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://angular-box-195401.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        this.context = contexts[0];
        try {
            return myApiService.findJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
