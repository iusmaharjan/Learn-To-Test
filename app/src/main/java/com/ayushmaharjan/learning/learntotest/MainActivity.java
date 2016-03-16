package com.ayushmaharjan.learning.learntotest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ayushmaharjan.learning.learntotest.idling.IdlingActivity;
import com.ayushmaharjan.learning.learntotest.intenttest.ContactsActivity;
import com.ayushmaharjan.learning.learntotest.intenttest.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_CONTACT = 15;

    public static String mCallerNumber;

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void sayHello(View v){
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }

    public void launchSecondActivity(View v) {
        startActivity(SecondActivity.launchActivity(this, "Test"));
    }

    public void launchIdlingResourceActivity(View v) {
        startActivity(new Intent(this, IdlingActivity.class));
    }

    public void pickContact(View v) {
        Intent contactIntent = new Intent(this, ContactsActivity.class);
        startActivityForResult(contactIntent, REQUEST_PICK_CONTACT);
    }

    public void launchDialer(View v) {
        Intent callIntent = createCallIntentFromNumber();
        startActivity(callIntent);
    }

    private Intent createCallIntentFromNumber() {
        mCallerNumber = editText.getText().toString();
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        intentToCall.setData(Uri.parse("tel:" + mCallerNumber));
        return intentToCall;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_PICK_CONTACT) {
            if(resultCode == RESULT_OK) {
                textView.setText(data.getExtras().getString(ContactsActivity.KEY_PHONE_NUMBER));
                editText.setText(data.getExtras().getString(ContactsActivity.KEY_PHONE_NUMBER));
            }
        }
    }
}