package com.example.sean.gradingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Delete extends Activity{



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_button);

        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<String> names = (ArrayList<String>) extra.getSerializable("names");
        final Spinner sp=(Spinner) findViewById(R.id.delete_spinner);
        ArrayAdapter<String> adp= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,names);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);
    }

    public void delete (View view) {
        Intent myIntent = new Intent(Delete.this, MainActivity.class);
        Spinner spinner = (Spinner)findViewById(R.id.delete_spinner);
        String button = spinner.getSelectedItem().toString();

        myIntent.putExtra("deletedButton", button);

        setResult(Activity.RESULT_OK, myIntent);
        finish();
    }
}
