package com.github.masekind.watcher.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.masekind.watcher.DiskLogger;
import com.github.masekind.watcher.Watcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Watcher.setLogger(new DiskLogger(this));

        Watcher.d("Testing some logging stuffs");
        Watcher.i("Log should go to stdout and to log file on disk");

        Watcher.e("Also lets see what an exception does", new Exception("this is an exception"));

        Watcher.v("Some more verbose messaging so lets just type a lot of stuff and see what it looks like");

        Watcher.w("You are now just fucking about");
    }
}
