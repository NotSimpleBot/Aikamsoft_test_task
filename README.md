# Aikamsoft_test_task

## Иформация по развертыванию проекта:

> Проект находится в ветке **master**.
>
> Прокт завершен, все необходимые для запуска файлы находятся в **Aikamsoft_test_task/out/artifacts/Aikamsoft_jar/**
>
> Дамп базы дынных находится в папке **Aikamsoft_test_task/out/artifacts/Aikamsoft_jar/DB_backups/** , сделал несколько бэкапов, так как не знаю какой метод восстановления будет использован. Используемые данные для коннекта к БД:
>
>>**URL** = "jdbc:postgresql://localhost:5432/aikamsoft_db"
>>
>>**USER** = "postgres"
>>
>>**PASSWORD** = "root"
>
> Имя jar файла **Aikamsoft.jar**, запуск требуется производить следующей коммандой:
> **java -jar Aikamsoft.jar search input.json output.json**

## Общее описание проекта:
>Необходимо разработать приложение, предоставляющее сервис работы с данными в БД. Данный сервис, на основании входных параметров(аргументы командной строки), типа операции и входного файла – извлекает необходимые данные из БД и формирует результат обработки в выходной файл. 
Все возможные ошибки должны быть обработаны и зафиксированы в выходном файле.

## Цели проекта:
1) :white_check_mark:Используемый стэк: java 8, PostgreSQL, Maven;
2) :white_check_mark:Входные параметры: тип операции, путь к входному файлу, путь к файлу результата;
3) :white_check_mark:Формат файлов: JSON;
4) :white_check_mark:Готовый проект должен быть загружен на github;
5) :white_check_mark:Готовый проект должен содержать готовый набор данных для тестирования: дамп БД, входные файлы;
6) :white_check_mark:Готовый проект должен включать инструкцию по сборке/запуску;
7) :white_check_mark:Entities:(**Customer, Product, Purchase**);
8) :white_check_mark:Определять тип операции по входному параметру (Operation.SEARCH, Operation.STAT);
9) :white_check_mark:Поиск по критериям **search** и **stat**;
10) :white_check_mark:В случае возникновения ошибки, при выполнении любой операции - фиксируется результат в файл.

## Краткое описание реализации проекта:
>

