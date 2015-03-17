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

        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout)findViewById(R.id.main_screen);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, layoutParams);

        Intent myIntent = new Intent(CreateNewClass.this, MainActivity.class);
        startActivity(myIntent);
    }

}