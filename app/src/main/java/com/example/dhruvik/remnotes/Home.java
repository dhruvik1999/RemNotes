package com.example.dhruvik.remnotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.Date;

public class Home extends AppCompatActivity {

    SharedPreferences memory;
    ListView listView;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initiallization();
        setAllNot();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Home.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("are you sure to delete?")
                        .setMessage("delete note..")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Data.allNotesList.remove(position);
                                try {
                                    Data.memory.edit().putString(Data.keyName,ObjectSerializer.serialize(Data.allNotesList)).apply();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                setAllNot();
                            }
                        }).setNegativeButton("no",null).show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditTask.position = position;
                startActivity(new Intent(getApplicationContext(),EditTask.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            startActivity(new Intent(getApplicationContext(),Add.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAllNot(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Data.allNotesList);
        listView.setAdapter(adapter);
    }

    private void initiallization(){
        data = new Data(this);
        listView =  (ListView)this.findViewById(R.id.list);
        memory = Data.memory;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
