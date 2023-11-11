package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ChangeStatus extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private Spinner spinner;

    private EditText textView;

    private EditText textV;

    private SharedPreferences.Editor sheard;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);
        Intent Intent = getIntent();
        spinner = findViewById(R.id.spinner);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textView = findViewById(R.id.textView);
        textV = findViewById(R.id.textV);

    }

    public void btnChangeOnClick(View v) {
        // Read JSON data from SharedPreferences
        String jsonData = sharedPreferences.getString("data", "");

        String input = textView.getText().toString().toLowerCase();

        // Convert JSON data back to your object (DataBase)
        Gson gson = new Gson();
        DataBase data = gson.fromJson(jsonData, DataBase.class);

        // Check if data is not null and contains tasks
        if (data != null && data.task != null && !data.task.isEmpty()) {
            boolean taskFound = false;

            for (int i = 0; i < data.task.size(); i++) {
                if (data.task.get(i).getName_Task().toLowerCase().equals(input)) {
                    // Update the status if the task is found
                    data.task.get(i).setStatus_Task("done");
                    taskFound = true;
                    break; // exit the loop since the task is found
                }
            }

            if (!taskFound) {
                // Show a message if the task is not found
                textV.setText("Sorry, the task is not found or the task is already done.");
            } else {
                // Save the updated data
                String updatedData = gson.toJson(data);
                sheard = sharedPreferences.edit();
                sheard.putString("data", updatedData);
                sheard.commit();

                // Optionally, provide feedback that the task status has been changed
                textV.setText("Task status changed to Done");

                // Reload data in the ListView
            //    readDataAndPopulateListView();
            }
        }

    }


    }


