package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolificinteractive.materialcalendarview.CalendarDay;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTodoFarmDTO {

    private int userTodoFarmId;
    private Long userId;
    private int farmId;
    private int todoId;

    private String task;

    private String date;

    private CalendarDay calendarDay;

    public UserTodoFarmDTO() {
    }

    public UserTodoFarmDTO(int userTodoFarmId, Long userId, int farmId, int todoId, String task, String date) {
        this.userTodoFarmId = userTodoFarmId;
        this.userId = userId;
        this.farmId = farmId;
        this.todoId = todoId;
        this.task = task;
        this.date = date;

        this.calendarDay = CalendarDay.from(Integer.parseInt(date.split("-")[0]),
                                            Integer.parseInt(date.split("-")[1]),
                                            Integer.parseInt(date.split("-")[2]));
    }


    public int getUserTodoFarmId() {
        return userTodoFarmId;
    }

    public void setUserTodoFarmId(int userTodoFarmId) {
        this.userTodoFarmId = userTodoFarmId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.calendarDay = CalendarDay.from(Integer.parseInt(date.split("-")[0]),
                Integer.parseInt(Integer.parseInt(date.split("-")[1])-1+""),
                Integer.parseInt(date.split("-")[2]));
        this.date = date;
    }

    public CalendarDay getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
    }
}
