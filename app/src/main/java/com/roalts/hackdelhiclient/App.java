package com.roalts.hackdelhiclient;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by raghav on 25/07/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "NIVI1mr35p6Am42KX01qrZkA5rv6xzhk3c2ttf2x", "3zEfmm9ZuL4XgEt9JJbnYqvTQfDNXkXDjOQdIq7D");
        ParseFacebookUtils.initialize(this);
    }
}