package com.example.coffe;

public class Task_Manger {

    private String Name_Task;
    private String status_Task;

    public Task_Manger() {

    }

    public Task_Manger(String name_Task,String Status_Task) {
        Name_Task = name_Task;
        status_Task=Status_Task;
    }

    public String getName_Task() {
        return Name_Task;
    }

    public String getStatus_Task() {
        return status_Task;
    }

    public void setName_Task(String name_Task) {
        Name_Task = name_Task;
    }

    public void setStatus_Task(String status_Task) {
        this.status_Task = status_Task;
    }

    @Override
    public String toString() {
        return "Task_Manger{" +
                "Name_Task='" + Name_Task + '\'' +
                ", status_Task='" + status_Task + '\'' +
                '}';
    }
}
