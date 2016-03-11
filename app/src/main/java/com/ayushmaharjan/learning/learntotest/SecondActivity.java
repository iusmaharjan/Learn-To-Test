package com.ayushmaharjan.learning.learntotest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static final String KEY = "key";

    public static Intent launchActivity(Context context, int value) {
        Intent intent = new Intent(context, SecondActivity.class);
        Bundle params = new Bundle();
        params.putInt(KEY, value);
        return intent;
    }
}
