package com.example.administrator.myring;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter());





//
//
//        findViewById(R.id.icon).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "icon", 0).show();
//                ;
//            }
//        });
//
//        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "tv1",0).show();;
//            }
//        });
//        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "tv2",0).show();;
//            }
//        });

    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return new Integer(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView != null){
                return convertView;
            }
            View  v =   LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false);
            return v;
        }
    }

}
