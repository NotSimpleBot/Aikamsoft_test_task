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
                    CustomersBetweenTwoDatesCriteria criteria = OBJECT_MAPPER.readValue(json, CustomersBetweenTwoDatesCriteria.class);
                    criteriasList.add(criteria);
                } catch (JsonProcessingException e) {
                    ErrorInJson.writeError(e);
                }
            }
        }
        criterias = new Criterias[criteriasList.size()];
        criteriasList.toArray(criterias);
        return criterias;
    }

    public static void saveAllCustomersToJsonOutputFile(File outputFile, ResultJsonObjectMarker resultJsonObject) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(outputFile, resultJsonObject);
        } catch (IOException e) {
            ErrorInJson.writeError(e);
        }

    }

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
            ErrorInJson.writeError(e);
        }
        return result;
    }
}
