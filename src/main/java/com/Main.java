package com;

import com.guzanov.helpers.Operation;
import com.guzanov.criterias.Criterias;
import com.guzanov.helpers.JsonHelper;
import com.guzanov.service.CustomersService;
import com.guzanov.service.CustomersServiceImpl;
import com.guzanov.deserialized_objects.ResultJsonObjectMarker;

import java.io.File;
import java.util.Locale;

public class Main {
    public static Operation operation;
    public static File pathToInputJsonFile;
    public static File pathToOutputJsonFile;
    private static final CustomersService CUSTOMERS_SERVICE =
            new CustomersServiceImpl();


    public static void main(String[] args) {
        startProgram(args);
    }


    public static void startProgram(String[] args) {
        operation = Operation.valueOf(args[0].toUpperCase(Locale.ROOT));
        pathToInputJsonFile = new File(args[1]);
        pathToOutputJsonFile = new File(args[2]);

        Criterias[] criteriasFromInputJsonFile = JsonHelper.getAllCriteriasFromJsonFile(pathToInputJsonFile, operation);
        ResultJsonObjectMarker theResultOfTheSearchForBuyersByCriteria = null;
        switch (operation) {
            case SEARCH:
                theResultOfTheSearchForBuyersByCriteria =
                        CUSTOMERS_SERVICE.getAllCustomersListsByCriteriaOperationSearch(criteriasFromInputJsonFile);
                break;
            case STAT:
                theResultOfTheSearchForBuyersByCriteria = CUSTOMERS_SERVICE.getAllCustomersListsByCriteriaOperationStat(criteriasFromInputJsonFile);
        }
        JsonHelper.saveAllCustomersToJsonOutputFile(pathToOutputJsonFile, theResultOfTheSearchForBuyersByCriteria);
    }

}
