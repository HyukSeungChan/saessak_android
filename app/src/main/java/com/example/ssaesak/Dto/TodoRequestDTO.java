package com.example.ssaesak.Dto;

public class TodoRequestDTO {

    private int todoId;
    private String date;
    private String task;

    public TodoRequestDTO() {
    }

    public TodoRequestDTO(int todoId, String date, String task) {
        this.todoId = todoId;
        this.date = date;
        this.task = task;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
