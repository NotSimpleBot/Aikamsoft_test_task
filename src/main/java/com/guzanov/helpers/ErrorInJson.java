package com.guzanov.helpers;

import com.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guzanov.Operation;

import java.io.IOException;

public class ErrorInJson {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Operation type = Operation.ERROR;
    private String message;

    public ErrorInJson() {
    }

    public ErrorInJson(String message) {
        this.message = message;
    }

    public static void writeError(Exception exception) {
        try {
            ErrorInJson errorInJson = new ErrorInJson(exception.getMessage());
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(Main.pathToOutputJsonFile, errorInJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public String getType() {
        return type.getStat();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
