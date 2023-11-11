package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    Intent intent = new Intent(MainActivity.this,TaskActivity.class);

                    startActivity(intent);

                }
                else if (position == 1) {

                    Intent intent = new Intent(MainActivity.this,ChangeStatus.class);

                    startActivity(intent);

                }
                else if (position == 2) {

                    Intent intent = new Intent(MainActivity.this, ListAllTask.class);

                    startActivity(intent);
                }
            }
        };

        ListView listView = (ListView)findViewById(R.id.listviwe);

        listView.setOnItemClickListener(itemClickListener);
    }

}