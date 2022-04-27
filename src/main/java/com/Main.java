package com;

import com.guzanov.Operation;
import com.guzanov.ResultJsonObject;
import com.guzanov.criterias.Criterias;
import com.guzanov.entity.Customer;
import com.guzanov.helpers.JsonHelper;
import com.guzanov.service.CustomersService;
import com.guzanov.service.CustomersServiceImpl;

import java.io.File;
import java.util.Locale;

public class Main {
    private static final CustomersService CUSTOMERS_SERVICE =
            new CustomersServiceImpl();

    public static void main(String[] args) {
        startProgram(args);
    }


    public static void startProgram(String[] args) {
        Operation operation = Operation.valueOf(args[0].toUpperCase(Locale.ROOT));
        File pathToInputJsonFile = new File(args[1]);
        File pathToOutputJsonFile = new File(args[2]);

        Criterias[] criteriasFromInputJsonFile = JsonHelper.getAllCriteriasFromJsonFile(pathToInputJsonFile);
        ResultJsonObject<Customer> theResultOfTheSearchForBuyersByCriteria =
                CUSTOMERS_SERVICE.getAllCustomersListsByCriterias(operation, criteriasFromInputJsonFile);
        JsonHelper.saveAllCustomersToJsonOutputFile(pathToOutputJsonFile, theResultOfTheSearchForBuyersByCriteria);
    }

}
