package com.example.dhruvik.remnotes;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.ArrayList;

public class Data {
    Context context;
    static ArrayList<String> allNotesList;
    static SharedPreferences memory;
    static String keyName = "SPAllNotes";

    public Data(Context context){
        this.context = context;
        memory = context.getSharedPreferences("com.example.dhruvik.remnotes",Context.MODE_PRIVATE);
        allNotesList = new ArrayList<String>();
        try {
            allNotesList = (ArrayList<String>) ObjectSerializer.deserialize(memory.getString(keyName,ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
