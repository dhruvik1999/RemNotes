package com.example.dhruvik.remnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;

public class EditTask extends AppCompatActivity {

    static int position;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editText  = (EditText)this.findViewById(R.id.enote);
        editText.setText(Data.allNotesList.get(position));

    }


    @Override
    protected void onPause() {
        super.onPause();
        Data.allNotesList.set(position,editText.getText().toString());
        try {
            Data.memory.edit().putString(Data.keyName,ObjectSerializer.serialize(Data.allNotesList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(getApplicationContext(),Home.class));
finish();
    }
}
