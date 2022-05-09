package com.guzanov.helpers;

import com.Main;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Класс для логгирования ошибок в исходящий .json файл
 */
public class ErrorInJson {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Operation type = Operation.ERROR;
    private String message;

    public ErrorInJson() {
    }

    public ErrorInJson(String message) {
        this.message = message;
    }

    /**
     * Записывает сообщение переданного исключения в результирующий Json файл (сереализует) и завершает работу программу.
     *
     * @param exception Исключение, будет записано в файл
     */
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
