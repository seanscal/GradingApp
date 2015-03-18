package com.example.sean.gradingapp;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CreateNewClass extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new);
    }

    protected void onResume() {
        super.onResume();
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
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