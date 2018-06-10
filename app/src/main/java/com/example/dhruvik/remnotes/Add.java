package com.example.dhruvik.remnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;

public class Add extends AppCompatActivity {


    EditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        notes = (EditText)this.findViewById(R.id.note);
    }

    public void saveNotes(String n){
        Data.allNotesList.add(n);
        try {
            Data.memory.edit().putString(Data.keyName,ObjectSerializer.serialize(Data.allNotesList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNotes( notes.getText().toString());
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();

    }
}
