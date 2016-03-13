package com.ayushmaharjan.learning.learntotest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }

    public void launchSecondActivity(View v) {
        startActivity(SecondActivity.launchActivity(this, "Test"));
    }

    public void launchIdlingResourceActivity(View v) {
        startActivity(new Intent(this, IdlingActivity.class));
    }

}
