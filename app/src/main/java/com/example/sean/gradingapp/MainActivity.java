package com.example.sean.gradingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity {
    ArrayList<String> names = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> buttonSet = prefs.getStringSet("saved_buttons", null);
        if(buttonSet != null){
            LinearLayout ll = (LinearLayout)findViewById(R.id.main_screen);
            for(String buttonText : buttonSet){
                Button button = new Button(this);
                button.setText(buttonText);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(button, layoutParams);
            }
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public void goToCreate (View view) {
        Intent myIntent = new Intent(MainActivity.this, CreateNewClass.class);
        MainActivity.this.startActivityForResult(myIntent, 0);
    }

    public void goToDelete (View view) {
        Bundle extra = new Bundle();
        extra.putSerializable("names", names);

        Intent myIntent = new Intent(MainActivity.this, Delete.class);
        myIntent.putExtra("extra", extra);
        MainActivity.this.startActivityForResult(myIntent, 1);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //you specified the request code before, when launching the second activity
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String buttonText = data.getStringExtra("button_text");
                if (buttonText != null) {
                    names.add(buttonText);
                    Button button = new Button(this);
                    button.setText(buttonText);
                    LinearLayout ll = (LinearLayout) findViewById(R.id.main_screen);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                    ll.addView(button, layoutParams);

                    //here comes the part that saves the button strings persistently
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Set<String> buttonSet = prefs.getStringSet("saved_buttons", null);
                    if (buttonSet == null) {
                        buttonSet = new HashSet<String>();
                    }
                    buttonSet.add(buttonText);
                    prefs.edit().putStringSet("saved_buttons", buttonSet).apply();
                }
            }
        }
    }
}