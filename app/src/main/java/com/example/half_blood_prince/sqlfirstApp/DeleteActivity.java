package com.example.half_blood_prince.sqlfirstapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteActivity extends ActionBarActivity {

    DatabaseHandler dbh;
    Cursor currentCursor;
    EditText ID;
    TextView Name,Phone,Sex;
    Button Submit,Delete;
    boolean flag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ID = (EditText) findViewById(R.id.id);
        Name = (TextView) findViewById(R.id.dName);
        Phone = (TextView) findViewById(R.id.dPhone);
        Sex = (TextView) findViewById(R.id.dSex);

        Submit = (Button) findViewById(R.id.submit);
        Delete = (Button) findViewById(R.id.delete);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Bean currentBean = new Bean();
                try{
                    currentBean.setmID(Integer.parseInt(ID.getText().toString()));

                }catch (NumberFormatException e) {
                    Toast.makeText(getBaseContext(),"Please Eneter a Valid Id",Toast.LENGTH_SHORT).show();
                }
                //currentBean.setmID(Integer.parseInt(ID.getText().toString()));
                dbh = new DatabaseHandler(getBaseContext());
                currentCursor = dbh.Read(currentBean);
                if (currentCursor.moveToFirst()) {
                    Name.setText(currentCursor.getString(currentCursor.getColumnIndex(dbh.KEY_NAME)));
                    Phone.setText(currentCursor.getString(currentCursor.getColumnIndex(dbh.KEY_PHONE)));
                    Sex.setText(currentCursor.getString(currentCursor.getColumnIndex(dbh.KEY_SEX)));
                    flag = true;

                } else {

                    Name.setText(" ");
                    Phone.setText(" ");
                    Sex.setText(" ");
                    Toast.makeText(getBaseContext(), "No Id Found", Toast.LENGTH_SHORT).show();
                    flag = false;
                }


                Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag) {
                            dbh = new DatabaseHandler(getBaseContext());
                            dbh.Delete(currentBean);
                            Toast.makeText(getBaseContext(), "ID No " + currentBean.getmID() + " Successfully Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });



    }


}
