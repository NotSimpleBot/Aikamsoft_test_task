package com.guzanov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guzanov.criterias.Criterias;
import com.guzanov.criterias.target.BadCustomersLessCriteria;
import com.guzanov.criterias.target.CustomersByLastNameCriteria;
import com.guzanov.criterias.target.CustomersProductAmountBetweenCriteria;
import com.guzanov.criterias.target.CustomersProductCountMoreCriteria;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainTest {
    Criterias[] criterias;

    @Test
    public void parseToObjectJSON() throws IOException {
//        String json = "{\"criterias\":[" +
//                "{\"lastName\":\"Иванов\"}," +
//                "{\"productName\":\"Минеральная вода\",\"minTimes\":5}," +
//                "{\"productName\":\"Пиво\",\"minTimes\":5}," +
//                "{\"minExpenses\":112,\"maxExpenses\":4000}," +
//                "{\"badCustomers\":3}" +
//                "]}";

//        ObjectMapper mapper1 = new ObjectMapper();
//        String json = mapper1.readValue(new File("C:\\Users\\guzan\\Desktop\\aikamsoft\\Project\\src\\main\\resources\\input.json"), String.class);
        StringBuilder json = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\guzan\\Desktop\\aikamsoft\\Project\\src\\main\\resources\\input.json"));
        while (reader.ready()){
            json.append(reader.readLine());
        }
        System.out.println(json.toString());


        List<Criterias> criteriasTmp = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

//        CustomersByLastNameCriteria t = new CustomersByLastNameCriteria("TestLastName");
//        String s = mapper.writeValueAsString(t);
//        System.out.println(s);
//        CustomersByLastNameCriteria tn = mapper.readValue(s, CustomersByLastNameCriteria.class);
//        System.out.println("tn: " + tn);

        JsonNode nodeOut = mapper.readTree(json.toString());
        Iterator<JsonNode> iteratorOut = nodeOut.elements();
        while (iteratorOut.hasNext()) {

            JsonNode nodeIn = iteratorOut.next();
            if (criterias == null) {
                criterias = new Criterias[nodeIn.size()];
            }
            Iterator<JsonNode> iteratorIn = nodeIn.elements();
            System.out.println("=========");
            while (iteratorIn.hasNext()) {
                Criterias c;
                JsonNode node = iteratorIn.next();
                System.out.println(node.toString());
                if (node.toString().contains("lastName")) {
                    c = mapper.readValue(node.toString(), CustomersByLastNameCriteria.class);
                    criteriasTmp.add(c);
                } else {
                    if (node.toString().contains("productName")) {
                        c = mapper.readValue(node.toString(), CustomersProductCountMoreCriteria.class);
                        criteriasTmp.add(c);
                    } else {
                        if (node.toString().contains("minExpenses")) {
                            c = mapper.readValue(node.toString(), CustomersProductAmountBetweenCriteria.class);
                            criteriasTmp.add(c);
                        } else {
                            if (node.toString().contains("badCustomers")) {
                                c = mapper.readValue(node.toString(), BadCustomersLessCriteria.class);
                                criteriasTmp.add(c);
                            }
                        }
                    }
                }
            }
            criteriasTmp.toArray(criterias);
            System.out.println(Arrays.toString(criterias));
        }


    }


}