package com.guzanov.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.*;
import com.guzanov.deserialized_objects.ResultJsonObjectMarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Содержит методы для работы с Json файлами и критериями.
 */
public class JsonHelper {
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper();

    /**
     * Преобразует Json файл в строку и парсит критерии.
     *
     * @param file      Путь до input файла с типом операции
     * @param operation Тип операции (search, stat)
     * @return Массив критериев
     */
    public static Criterias[] getAllCriteriasFromJsonFile(File file, Operation operation) {
        String json = getJsonInString(file);
        return parsingTheCriteria(json, operation);
    }

    /**
     * Разбирвет Json представленный в виде строки и в зависимости от типа переданной операции десериализует критерии.
     *
     * @param json      Json представленный в виде строки
     * @param operation Тип операции (search, stat)
     * @return Массив критериев
     */
    public static Criterias[] parsingTheCriteria(String json, Operation operation) {
        List<Criterias> criteriasList = new ArrayList<>();

        if (operation == Operation.SEARCH) {
            criteriasList = iterateForAllJsonNode(json);
        } else {
            if (operation == Operation.STAT) {
                try {
                    CustomersBetweenTwoDatesCriteria criteria = OBJECT_MAPPER.readValue(json, CustomersBetweenTwoDatesCriteria.class);
                    criteriasList.add(criteria);
                } catch (JsonProcessingException e) {
                    ErrorInJson.writeError(e);
                }
            }
        }
        return criteriasList.toArray(new Criterias[0]);
    }

    /**
     * Сериализует результирующий объект в json файл.
     *
     * @param outputFile       Файл, в который происходит запись объекта
     * @param resultJsonObject Объект для сериализации - совокупность покупателей
     *                         с дополнительными статистическими данными
     */
    public static void saveAllCustomersToJsonOutputFile(File outputFile, ResultJsonObjectMarker resultJsonObject) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(outputFile, resultJsonObject);
        } catch (IOException e) {
            ErrorInJson.writeError(e);
        }

    }

    /**
     * Преобразует Json в строку.
     *
     * @param file Файл, который будет преобразован в строку
     * @return Строку - преобразованный файл
     */
    private static String getJsonInString(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (IOException ioException) {
            ErrorInJson.writeError(ioException);
        }
        return sb.toString();
    }

    /**
     * Обеспечивает представление критериев в виде Java объекта (десериализация).
     *
     * @param result В этот список будет происходить запись каждой десериализованной критерии
     * @param node   1 элементы из общего набора критериев, представленных в json файле
     * @throws JsonProcessingException если не удается десериализовать 'node'
     */
    private static void checkCriteriasInJson(List<Criterias> result, JsonNode node) throws JsonProcessingException {
        Criterias c;

        if (node.toString().contains("lastName")) {
            c = OBJECT_MAPPER.readValue(node.toString(), CustomersByLastNameCriteria.class);
            result.add(c);
        } else {
            if (node.toString().contains("productName")) {
                c = OBJECT_MAPPER.readValue(node.toString(), CustomersProductCountMoreCriteria.class);
                result.add(c);
            } else {
                if (node.toString().contains("minExpenses")) {
                    c = OBJECT_MAPPER.readValue(node.toString(), CustomersProductAmountBetweenCriteria.class);
                    result.add(c);
                } else {
                    if (node.toString().contains("badCustomers")) {
                        c = OBJECT_MAPPER.readValue(node.toString(), BadCustomersLessCriteria.class);
                        result.add(c);
                    }
                }
            }
        }
    }

    /**
     * Возвращает список критериев из строкового представления json.
     * <p>
     * Преобраезует строковое представление json в набор 'node', каждая отдельная нода - самодостаточный критерий.
     *
     * @param json Строковое представление json
     * @return Список критериев
     */
    private static List<Criterias> iterateForAllJsonNode(String json) {
        List<Criterias> result = new ArrayList<>();
        JsonNode nodeOut;
        Iterator<JsonNode> iteratorOut;
        try {
            nodeOut = OBJECT_MAPPER.readTree(json);
            iteratorOut = nodeOut.elements();
            while (iteratorOut.hasNext()) {
                JsonNode nodeIn = iteratorOut.next();
                Iterator<JsonNode> iteratorIn = nodeIn.elements();
                while (iteratorIn.hasNext()) {
                    JsonNode node = iteratorIn.next();
                    checkCriteriasInJson(result, node);
                }
            }
        } catch (JsonProcessingException e) {
            ErrorInJson.writeError(e);
        }
        return result;
    }
}
