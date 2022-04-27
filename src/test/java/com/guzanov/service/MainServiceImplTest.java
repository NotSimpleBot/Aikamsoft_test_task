package com.guzanov.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainServiceImplTest {
    private CustomersServiceImpl service;

    @BeforeClass
    public static void upConnect(){

    }

    @Test
    public void getAllCustomersByLastName() {
        List<String> expected = new ArrayList<>(Arrays.asList("Dmitriy", "Natalya"));
//        List<Customer> tmp = service.getAllCustomersByProductMinTimes("Tomatos",1);
        List<String> result = new ArrayList<>();
//        for (Customer t : tmp){
//            result.add(t.getFirstName());
//        }
        System.out.println(expected);
        System.out.println(result);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void getAllCustomersByProductMinTimes() {
    }

    @Test
    public void getAllCustomersByAmountInIntervalMinAndMax() {
    }

    @Test
    public void getAllBadCustomersByCountLessThen() {
    }
}