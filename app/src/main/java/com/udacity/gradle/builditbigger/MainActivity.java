package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.androidlibjoke.ShowJokeActivity;
import com.example.android.javalibjoke.ShowJoke;
import com.udacity.gradle.builditbigger.task.EndpointsAsyncTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        ShowJoke joke = new ShowJoke();
        Toast.makeText(this, joke.getJoke(), Toast.LENGTH_SHORT).show();
    }

    public void tellJokeApp(View view){
        ShowJoke joke = new ShowJoke();
        Intent intent = new Intent(this, ShowJokeActivity.class );
        intent.putExtra(ShowJokeActivity.TAG, joke.getJoke());
        startActivity(intent);
    }

    public void tellJokeAPI(View view){
         new EndpointsAsyncTask().execute(this);
    }


}
