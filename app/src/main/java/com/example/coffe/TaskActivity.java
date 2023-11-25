package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class TaskActivity extends AppCompatActivity {
    private EditText edittxt1;
    private EditText edittxt2;
    private final static String Data = "data";
    private SharedPreferences shared;
    private SharedPreferences.Editor sheard;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task);

        Intent intent = getIntent();

        edittxt1 = findViewById(R.id.edittxt1);  //Hoks in

        edittxt2 = findViewById(R.id.edittxt2);

        textView = findViewById(R.id.textView);

        // Initialize SharedPreferences
        shared = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public void btnsaveOnClick(View v) {

        String input1 = edittxt1.getText().toString().toLowerCase();

        String input2 = edittxt2.getText().toString().toLowerCase();

        if (input1.isEmpty() || input2.isEmpty()) {

            textView.setText("Please Enter All Fields");

        } else {
            if (input2.equals("done") || input2.equals("due")) {
                String existingData = shared.getString(Data, "");

                Gson gson = new Gson();

                DataBase existingDataBase = gson.fromJson(existingData, DataBase.class);


                if (existingDataBase == null) {

                    existingDataBase = new DataBase();
                }
                else {
                    for (int i = 0; i < existingDataBase.task.size(); i++) {
                        if (existingDataBase.task.get(i).getName_Task().equals(input1)) {
                            textView.setText("Task is Found");
                            return;
                        }
                }
                   existingDataBase.task.add(new Task_Manger(input1, input2));
                }

                String updatedData = gson.toJson(existingDataBase);

                sheard = shared.edit();

                sheard.putString(Data, updatedData);

                sheard.commit();

                textView.setText("Add TasK is Suffally");

            } else {
                textView.setText("Enter Status Done OR Due ");
            }
        }
    }
}