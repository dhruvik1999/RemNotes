package com.example.dhruvik.remnotes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_home);

        Wait wait = new Wait();
        wait.execute();
    }

    public class Wait extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent open = new Intent(getApplicationContext(),Home.class);
            startActivity(open);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
