package com.example.twentyone.model.data;

public class RegisterErrorResponseData {
    // Attributes
    private String entityName;
    private String errorKey;
    private String type;
    private String title;
    private Integer status;
    private String message;
    private String params;

    // Constructor
    public RegisterErrorResponseData(String entityName, String errorKey, String type, String title,
                                     Integer status, String message, String params) {
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.type = type;
        this.title = title;
        this.status = status;
        this.message = message;
        this.params = params;
    }

    // Getters & Setters
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public String getErrorKey() {
        return errorKey;
    }
    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }
}
