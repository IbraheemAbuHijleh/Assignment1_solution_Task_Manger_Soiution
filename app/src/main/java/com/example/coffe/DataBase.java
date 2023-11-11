package com.example.coffe;

import java.util.ArrayList;

public class DataBase {
    ArrayList<Task_Manger>  task =new ArrayList<>();

    public DataBase() {

    }


    @Override
    public String toString() {
        return "DataBase{" +
                "task=" + task +
                '}';
    }
}
