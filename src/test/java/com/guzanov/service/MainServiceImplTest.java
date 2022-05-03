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
        List<String> result = new ArrayList<>();

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