package com.guzanov.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guzanov.Operation;
import com.guzanov.ResultJsonObject;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.*;
import com.guzanov.entity.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Criterias[] getAllCriteriasFromJsonFile(File file, Operation operation) {
        Criterias[] criterias;
        String json = getJsonInString(file);
        List<Criterias> criteriasList = new ArrayList<>();

        if (operation == Operation.SEARCH) {
            criteriasList = iterateForAllTreeNode(json);
        } else {
            if (operation == Operation.STAT) {
                try {
                    System.out.println(json);
//                    criteriasList = Arrays.asList(OBJECT_MAPPER.readValue(json, CustomersBetweenTwoDatesCriteria[].class));
                    CustomersBetweenTwoDatesCriteria criteria = OBJECT_MAPPER.readValue(json, CustomersBetweenTwoDatesCriteria.class);
                    criteriasList.add(criteria);
                    // TODO: 28.04.2022 можно попробовать с помощью Calendar проитерироваться по всем промежуточным датам и запимать в Лист те, которые не Суббота и Восскресенье
                    // потом в ДАО итерироваться по полученному списку и передавать каждую дату в запрос, из запроса получать промежуточный результат и класть его в основной результат

                } catch (JsonProcessingException e) {
                    // TODO: 28.04.2022 LOG
                    e.printStackTrace();
                }
            }
        }
        criterias = new Criterias[criteriasList.size()];
        criteriasList.toArray(criterias);
        System.out.println(Arrays.toString(criterias));
        return criterias;
    }

    public static void saveAllCustomersToJsonOutputFile(File outputFile, ResultJsonObject<Customer> resultJsonObject) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(outputFile, resultJsonObject);
        } catch (IOException e) {
            // TODO: 27.04.2022 LOG
            e.printStackTrace();
        }

    }

    private static String getJsonInString(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (IOException ioException) {
            // TODO: 27.04.2022 LOG
            ioException.printStackTrace();
        }
        return sb.toString();
    }

    private static void checkCriteriasInJson(List<Criterias> result, JsonNode node, ObjectMapper mapper) throws JsonProcessingException {
        Criterias c;

        if (node.toString().contains("lastName")) {
            c = mapper.readValue(node.toString(), CustomersByLastNameCriteria.class);
            result.add(c);
        } else {
            if (node.toString().contains("productName")) {
                c = mapper.readValue(node.toString(), CustomersProductCountMoreCriteria.class);
                result.add(c);
            } else {
                if (node.toString().contains("minExpenses")) {
                    c = mapper.readValue(node.toString(), CustomersProductAmountBetweenCriteria.class);
                    result.add(c);
                } else {
                    if (node.toString().contains("badCustomers")) {
                        c = mapper.readValue(node.toString(), BadCustomersLessCriteria.class);
                        result.add(c);
                    }
                }
            }
        }
    }

    private static List<Criterias> iterateForAllTreeNode(String json) {
        List<Criterias> result = new ArrayList<>();
        JsonNode nodeOut;
        Iterator<JsonNode> iteratorOut;
        ObjectMapper mapper = new ObjectMapper();
        try {
            nodeOut = mapper.readTree(json);
            iteratorOut = nodeOut.elements();
            while (iteratorOut.hasNext()) {
                JsonNode nodeIn = iteratorOut.next();
                Iterator<JsonNode> iteratorIn = nodeIn.elements();
                while (iteratorIn.hasNext()) {
                    JsonNode node = iteratorIn.next();
                    checkCriteriasInJson(result, node, mapper);
                }
            }
        } catch (JsonProcessingException e) {
            // TODO: 27.04.2022 LOG
            e.printStackTrace();
        }
        return result;
    }
}
