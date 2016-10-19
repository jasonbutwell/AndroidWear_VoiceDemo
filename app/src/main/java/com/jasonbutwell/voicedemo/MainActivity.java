package com.jasonbutwell.voicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;

    private final int SpeechIntentRequestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Address the text view to display the result
        mTextView = (TextView) findViewById(R.id.text);

        // Create an intent for the speech recognizer
        Intent i  = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);   // setup data for the intent
        startActivityForResult( i, SpeechIntentRequestCode);    // call the intent

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                // main part of OnCreate()

            }
        });
    }

    // Called to receive the result of the Intent

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check the request code

        if ( requestCode == SpeechIntentRequestCode && resultCode == RESULT_OK ) {

            // Create a list and get a possible list of speech to text translations back

            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String textResult = results.get(0).toString();  // get only the first result and convert it to a string
            mTextView.setText(textResult);                  // Set the view we have created so we can see the results
        }
    }
}
