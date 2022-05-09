package com.guzanov.helpers;

/**
 * Содержит основные типы возможных операций.
 */
public enum Operation {
    SEARCH("search"),
    STAT("stat"),
    ERROR("error");


    private String stat;

    Operation(String stat) {
        this.stat = stat;
    }

    /**
     * Возвращает строковое представление операции.
     *
     * @return Операция в виде строки
     */
    public String getStat() {
        return stat;
    }
}
