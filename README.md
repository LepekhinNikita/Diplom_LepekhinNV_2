# Инструкция подключения БД и запуска SUT
1. Склонировать проект из репозитория командой ``` git clone ```
1. Открыть склонированный проект в Intellij IDEA
1. Для запуска контейнеров с MySql, PostgreSQL и Node.js использовать команду ``` docker-compose up -d --force-recreate ```
1. Запуск SUT
- для MySQL ввести в терминале команду

``` java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app  -jar artifacts/aqa-shop.jar```

- для PostgreSQL ввести в терминале команду

``` java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app  -jar artifacts/aqa-shop.jar ```

5. Запуск тестов (Allure)
-  для запуска на MySQL ввести команду

``` ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" ```

- для запуска на PostgreSQ ввести команду

``` ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" ```

6. Для получения отчета Allure в браузере ввести команду ``` ./gradlew allureServe ```
7. После окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой ``` docker-compose down ```