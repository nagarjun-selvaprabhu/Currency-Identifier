package com.example.currencyidentifier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class Output extends AppCompatActivity {
    TextToSpeech textToSpeech;
    String currencyValue;
    Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_output);

        TextView value ;
        value = (TextView) findViewById(R.id.textView) ;
        currencyValue = getIntent().getStringExtra("currencyValue");
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(ENGLISH);
                    textToSpeech.speak(currencyValue+"rupees",TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        value.setText("₹ "+currencyValue);
        homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.shutdown();
                finish();
            }
        });
        
    }
}