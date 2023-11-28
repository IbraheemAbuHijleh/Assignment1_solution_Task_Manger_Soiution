package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ChangeStatus extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private CheckBox checkBox;

    private EditText textView;

    private TextView textV;

    private SharedPreferences.Editor sheard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);
        Intent Intent = getIntent();
        checkBox = findViewById(R.id.checkBox);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textView = findViewById(R.id.textView);
        textV = findViewById(R.id.textV);

    }

    public void btnChangeOnClick(View v) {
        // Read JSON data from SharedPreferences
        String jsonData = sharedPreferences.getString("data", "");

        String input = textView.getText().toString().toLowerCase();

        String sep = checkBox.getText().toString().toLowerCase();

        // Convert JSON data back to your object (DataBase)
        Gson gson = new Gson();
        DataBase data = gson.fromJson(jsonData, DataBase.class);

        // Check if data is not null and contains tasks
        if (data != null && data.task != null && !data.task.isEmpty()) {
            for (int i = 0; i < data.task.size(); i++) {
                if(checkBox.isChecked()){
                if (data.task.get(i).getName_Task().toLowerCase().equals(input)) {
                    if (data.task.get(i).getStatus_Task().toLowerCase().equals("due")) {
                            data.task.get(i).setStatus_Task("done");
                            textV.setText("Task status changed to Done");
                    } else {
                        // The task is already done
                        textV.setText("The task is already done.");
                    }
                    break; // exit the loop since the task is found
                }
                else{
                    textV.setText("Sorry, the task is not found.");
                }
            }else{
                    textV.setText("Please Check done.");
                }
                }



            String updatedData = gson.toJson(data);
            sheard = sharedPreferences.edit();
            sheard.putString("data", updatedData);
            sheard.commit();


        }
    }
}