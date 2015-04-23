package com.example.sean.gradingapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateNewClass extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new);
    }

    protected void onResume() {
        super.onResume();
    }

    public void submit (View view) {
        Intent myIntent = new Intent(CreateNewClass.this, MainActivity.class);
        EditText mEdit   = (EditText)findViewById(R.id.class_name);
        String name = mEdit.getText().toString();

        mEdit   = (EditText)findViewById(R.id.class_semester);
        String semester = mEdit.getText().toString();

        mEdit   = (EditText)findViewById(R.id.class_year);
        String year = mEdit.getText().toString();

        String buttonText = name + " " + semester + " " + year;

        myIntent.putExtra("button_text", buttonText);

        setResult(Activity.RESULT_OK, myIntent);
        finish();
    }
}