package com.lfernandes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private String error;
    private String field;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public static Response success(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response;
    }

    public static Response error(String error, String field) {
        Response response = new Response();
        response.setError(error);
        response.setField(field);
        return response;
    }
    }
