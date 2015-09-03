package com.example.half_blood_prince.sqlfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Activity {
    //Main Layout Buttons
    Button Insert , Update , Delete , Display ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Insert = (Button) findViewById(R.id.insert);
        Update = (Button) findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);
        Display = (Button) findViewById(R.id.display);




        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertActivity = new Intent(getBaseContext(),InsertActivity.class);
                startActivity(insertActivity);
            }
        });



        Display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayActivity = new Intent(getBaseContext(),DisplayActivity.class);
                startActivity(displayActivity);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateActivity = new Intent(getBaseContext(),UpdateActivity.class);
                startActivity(updateActivity);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteActivity = new Intent(getBaseContext(),DeleteActivity.class);
                startActivity(deleteActivity);
            }
        });

    }


}
