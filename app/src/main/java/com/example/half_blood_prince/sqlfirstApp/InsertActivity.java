package com.example.half_blood_prince.sqlfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Half-Blood-Prince on 02-09-2015.
 */
public class InsertActivity extends Activity {

    EditText Name , Phone;
    Button Submit;

    RadioGroup Rg ;

    RadioButton Rb_male , Rb_female;

    Bean currentBean;

    DatabaseHandler dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);

        Name = (EditText) findViewById(R.id.name);
        Phone = (EditText) findViewById(R.id.phone);
        Submit = (Button) findViewById(R.id.submit);

        Rg = (RadioGroup) findViewById(R.id.sex);

        Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mName = Name.getText().toString();
                        String mPhone = Phone.getText().toString();

                        int sexSelected = Rg.getCheckedRadioButtonId();
                        RadioButton sex = (RadioButton) findViewById(sexSelected);
                        String mSex = sex.getText().toString();

                        // Storing the Name and Phone number in Bean Object , so that it can be passed as a whole object
                        currentBean = new Bean(mName,mPhone,mSex);
                        dbh = new DatabaseHandler(getBaseContext());
                        dbh.insert(currentBean);
                        Toast.makeText(getBaseContext(),"Name ="+currentBean.getmName()+"\nPhone No"+currentBean.getmPhone()+"\nSex"+currentBean.getmSex(),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
