package com;

import com.guzanov.criterias.Criterias;
import com.guzanov.deserialized_objects.ResultJsonObjectMarker;
import com.guzanov.helpers.JsonHelper;
import com.guzanov.helpers.Operation;
import com.guzanov.service.CustomersService;
import com.guzanov.service.CustomersServiceImpl;

import java.io.File;
import java.util.Locale;

/**
 * Содеожит основной метод для вхождения в программу.
 */
public class Main {
    public static Operation operation;
    public static File pathToInputJsonFile;
    public static File pathToOutputJsonFile;
    private static final CustomersService CUSTOMERS_SERVICE =
            new CustomersServiceImpl();

    /**
     * Метод вхождения в программу
     */
    public static void main(String[] args) {
        startProgram(args);
    }


    /**
     * Вызывает методы для парсинга коммандной строки, получения результирующего Json объекта и записи его в файл.
     */
    public static void startProgram(String[] args) {
        parsingTheCommandLine(args);
        saveResultJsonObject(createResultJsonObject());
    }


    /**
     * Парсит коммандную строку и записывает результат в статические поля Main класса.
     *
     * @param args массив строк - элементы переданные в коммандную стоку
     */
    public static void parsingTheCommandLine(String[] args) {
        operation = Operation.valueOf(args[0].toUpperCase(Locale.ROOT));
        pathToInputJsonFile = new File(args[1]);
        pathToOutputJsonFile = new File(args[2]);
    }

    /**
     * Создает результирующий объект, который будет записываться в файл.
     * <p>
     * Объект создается на основе типа операции и критериев, указанных во входном файле (pathToInputJsonFile).
     *
     * @return Объект для последующей сериализации в Json файл
     */
    public static ResultJsonObjectMarker createResultJsonObject() {
        ResultJsonObjectMarker jsonObject = null;
        switch (operation) {
            case SEARCH:
                jsonObject =
                        CUSTOMERS_SERVICE.getAllCustomersListsByCriteriaOperationSearch(parsingTheCriteriaInTheJsonFile(pathToInputJsonFile));
                break;
            case STAT:
                jsonObject = CUSTOMERS_SERVICE.getAllCustomersListsByCriteriaOperationStat(parsingTheCriteriaInTheJsonFile(pathToInputJsonFile));
        }
        return jsonObject;
    }

    /**
     * Сохраняет результирующий объект в Json файл.
     */
    private static void saveResultJsonObject(ResultJsonObjectMarker theResultOfTheSearchForBuyersByCriteria) {
        JsonHelper.saveAllCustomersToJsonOutputFile(pathToOutputJsonFile, theResultOfTheSearchForBuyersByCriteria);
    }

    /**
     * Разбирает входной Json файл (поиск критериев).
     */
    public static Criterias[] parsingTheCriteriaInTheJsonFile(File inputJsonFile) {
        return JsonHelper.getAllCriteriasFromJsonFile(pathToInputJsonFile, operation);
    }
}
