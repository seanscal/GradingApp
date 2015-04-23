package com.example.sean.gradingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
                        (LinearLayout.LayoutParams.MATCH_PARENT,
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

        Intent newIntent = new Intent(MainActivity.this, Delete.class);
        newIntent.putExtra("extra", extra);
        MainActivity.this.startActivityForResult(newIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Set<String> buttonSet = null;
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

                    //saves strings persistantly
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    buttonSet = prefs.getStringSet("saved_buttons", null);
                    if (buttonSet == null) {
                        buttonSet = new HashSet<>();
                    }
                    buttonSet.add(buttonText);

                    prefs.edit().putStringSet("saved_buttons", buttonSet).apply();
                }
            }
        }

        if (requestCode == 1) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            buttonSet = prefs.getStringSet("saved_buttons", null);

            if (resultCode == Activity.RESULT_OK) {
                String deleted = data.getStringExtra("deletedButton");
                if (buttonSet != null) {
                    for (Iterator<String> it = buttonSet.iterator(); it.hasNext(); ) {
                        String f = it.next();
                        if (f.equals(deleted))
                            buttonSet.remove(f);
                }

                    prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putStringSet("saved_buttons", buttonSet).apply();
                }
            }
        }
    }
}