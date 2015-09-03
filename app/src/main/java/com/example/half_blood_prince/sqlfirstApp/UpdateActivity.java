package com.example.half_blood_prince.sqlfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class UpdateActivity extends Activity {

    DatabaseHandler dbh;

    EditText ID,Name,Phone;
    Button Update;
    RadioGroup RG ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        ID = (EditText) findViewById(R.id.Id);
        Name = (EditText) findViewById(R.id.uName);
        Phone = (EditText) findViewById(R.id.uPhone);
        Update = (Button) findViewById(R.id.update);
        RG = (RadioGroup) findViewById(R.id.uSex);


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh = new DatabaseHandler(getBaseContext());

                int sexSelected = RG.getCheckedRadioButtonId();
                final RadioButton updatedSex = (RadioButton) findViewById(sexSelected);

                String mId = ID.getText().toString();
                String mName = Name.getText().toString();
                String mPhone = Phone.getText().toString();
                String mSex = updatedSex.getText().toString();



                Bean currentBean = new Bean(Integer.parseInt(mId),mName,mPhone,mSex);

                dbh.Update(currentBean);
                Toast.makeText(getBaseContext(),"Successfully Updated", Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }

}
