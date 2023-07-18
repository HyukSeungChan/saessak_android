package com.example.ssaesak.Dto;

public class UserTodoFarmResponseDto {

    private int userTodoFarmId;
    private Long userId;
    private int todoId;
    private int farmId;

    // 일자리 정보
    private String task;

    // 노동자 정보
    private String name;
    private String profile_image;


    public UserTodoFarmResponseDto() {
    }

    public UserTodoFarmResponseDto(int userTodoFarmId, Long userId, int todoId, int farmId, String task, String name, String profile_image) {
        this.userTodoFarmId = userTodoFarmId;
        this.userId = userId;
        this.todoId = todoId;
        this.farmId = farmId;
        this.task = task;
        this.name = name;
        this.profile_image = profile_image;
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

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
