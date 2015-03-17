package com.example.sean.gradingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateNewClass extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        setContentView(R.layout.create_new);
    }

    public void Submit (View view) {

        Button myButton = new Button(this);
        EditText mEdit   = (EditText)findViewById(R.id.class_name);
        String name = mEdit.getText().toString();

        mEdit   = (EditText)findViewById(R.id.class_semester);
        String semester = mEdit.getText().toString();

        mEdit   = (EditText)findViewById(R.id.class_year);
        String year = mEdit.getText().toString();

        myButton.setText(name + " " + semester + " " + year);


        LinearLayout ll = (LinearLayout)findViewById(R.id.main_screen);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);

        Intent myIntent = new Intent(CreateNewClass.this, MainActivity.class);
        myIntent
        CreateNewClass.this.startActivity(myIntent);
    }

}