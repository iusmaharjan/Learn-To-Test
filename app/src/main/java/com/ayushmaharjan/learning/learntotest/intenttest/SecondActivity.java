package com.ayushmaharjan.learning.learntotest.intenttest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ayushmaharjan.learning.learntotest.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String value = getIntent().getExtras().getString(KEY);

        ((TextView)findViewById(R.id.textView1)).setText(value);
    }

    public static final String KEY = "EXTRA";

    public static Intent launchActivity(Context context, String value) {
        Intent intent = new Intent(context, SecondActivity.class);
        Bundle params = new Bundle();
        params.putString(KEY, value);
        intent.putExtra(KEY, value);
        return intent;
    }
}
