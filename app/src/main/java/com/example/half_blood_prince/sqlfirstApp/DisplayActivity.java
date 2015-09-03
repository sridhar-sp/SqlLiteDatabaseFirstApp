package com.example.half_blood_prince.sqlfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class DisplayActivity extends Activity {

    TextView Name , Phone;

    ListView listView ;

    List<Bean> studentDetails = new ArrayList<>();

    List<Bean> resultSet = new ArrayList<>();

    ViewHolder holder;

    DatabaseHandler dbh;

    Bean currentBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Name = (TextView) findViewById(R.id.display_name);
        Phone = (TextView) findViewById(R.id.display_phone);
        listView = (ListView) findViewById(R.id.list_item);

       dbh=new DatabaseHandler(this);
       studentDetails = dbh.display();

        Iterator<Bean> itr = studentDetails.iterator();

        while (itr.hasNext()){
            currentBean = new Bean();
            currentBean = itr.next();
            resultSet.add(currentBean);
            Log.d("list",currentBean.getmID()+" "+currentBean.getmName()+" "+currentBean.getmPhone()+" "+currentBean.getmSex());
        }
//        for (Object sd:studentDetails){
//            currentBean = new Bean();
//            currentBean = (Bean) sd;
//            Name.setText(currentBean.getmName());
//            Phone.setText(currentBean.getmPhone());
//            Log.e("List","Inside for loop");
//            Log.d("LIST",currentBean.getmName()+currentBean.getmPhone());
//
//        }
        populateListView();


    }

    public static class ViewHolder{
        ImageView icon;
        TextView name , phone;
    }
    private void populateListView() {
        ArrayAdapter adapter = new MyCustomAdapter();
        listView.setAdapter(adapter);

    }


    private class MyCustomAdapter extends ArrayAdapter {
        MyCustomAdapter(){
            super(getBaseContext(),R.layout.custom_display,resultSet);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView = getLayoutInflater().inflate(R.layout.custom_display,parent,false);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.display_icon);
                holder.name = (TextView) convertView.findViewById(R.id.display_name);
                holder.phone = (TextView) convertView.findViewById(R.id.display_phone);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            Bean currentResult = resultSet.get(position);
            if(currentResult.getmSex().equalsIgnoreCase("male")){
                holder.icon.setImageResource(R.drawable.male);
            }else {
                holder.icon.setImageResource(R.drawable.female);
            }

            holder.name.setText(currentResult.getmName());
            holder.phone.setText(currentResult.getmPhone());

            return convertView;
        }
    }
}
