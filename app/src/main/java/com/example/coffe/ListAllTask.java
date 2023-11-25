package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;

import java.util.prefs.Preferences;

public class ListAllTask extends AppCompatActivity {

    private ListView listView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_task);
        Intent intent = getIntent();


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        listView=findViewById(R.id.listView);
        // Read data from SharedPreferences and populate ListView
        ListData();

    }

    public void ListData() {
        // Read JSON data from SharedPreferences
        String jsonData = sharedPreferences.getString("data", "");

        // Convert JSON data back to your object (DataBase)
        Gson gson = new Gson();
        DataBase data = gson.fromJson(jsonData, DataBase.class);

        // Check if data is not null and contains tasks
        if (data != null && data.task != null && !data.task.isEmpty()) {
            // Create an ArrayList of strings to hold the names and statuses
            ArrayList<String> taskStrings = new ArrayList<>();
            taskStrings.clear();
            for (Task_Manger taskManager : data.task) {
                if(taskManager.getStatus_Task().equals("due")) {
                    taskStrings.add("Name: " + taskManager.getName_Task() + ", Status: " + taskManager.getStatus_Task());
                }
            }

            // Create an ArrayAdapter and set it to the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskStrings);
            listView.setAdapter(adapter);
        }
    }

}