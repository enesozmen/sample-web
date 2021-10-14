# Web Project

This project is developed by using selenium with java. 

For reporting Allure framework is used.

## Requirements
- Java 
- Allure [Installation]
- Chrome and Firebox browsers
## Execution

You can use your ide to run tests one by one. The Chrome browser is selected as default browser.

If you want to execute cross browser tests and get allure reports
, you should use simple maven commands. This command will execute your tests and creates allure-reports folder at project's root directory.

```bash
mvn test
```

## Generate Report

Run below command at project's root directory. It will create and serve reports of executed tests.

```bash
allure serve allure-results
```


[Installation]: <https://docs.qameta.io/allure/#_installing_a_commandline>