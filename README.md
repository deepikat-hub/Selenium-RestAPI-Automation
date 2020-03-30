## Introduction -

1. This framework is a multiple project framework which consist of UI functional automation for Bewakoof.com and API automation using rest assured for github Teams api.
2. The number of test cases are limited as of now but can be easily added later. 

NOTE : The projects download gradle dependencies when run for the first time. Subsequent runs will be faster.

## Instructions to run:
1. `Run via CLI` : Assuming you have cloned the project freecharge-automation, execute the following
```
 Cd freecharge-automation, 
./gradlew clean test -b bewakoof-ui-automation/build.gradle . 
```
A allure report is by default opened in the browser after the test is completed. 
Similarly for github api test the command to execute is as follows:
```
 cd freecharge-automation
./gradlew clean test -b github-api-automation/build.gradle 
```
2. `Run via xml file` : This is available only for the ui automation right now. 
 Right click on the xml file name or from within the open xml file in eclipse. Select ‘Run as testNG suite’. This can be later used to run targeted tests based on group tags, different xml for Smoke or other tests etc using Jenkins.

3. `Run class via Eclipse/ your IDE` : Navigate to bewakoof-ui-automation. Click on src-> test—>java—>Bewakoof—>Login.java. Right click on the file and select ‘Run as testNG suite’. 
For api navigate to github-api-automation—> src-> test—>java—>GitHubAPITest—> TeamsAPITest.java. Right click and run as a testNG suite

## Reports:
1. Navigate to the project say in CLI and execute 
``` cd <TO your project> 
    ../gradlew allureServe 
    ```
In Eclipse, there are default testing reports as well available at test-output folder under the project.

## Logs:
1. bewakoof-ui-automation: Navigate to bewakoof-ui-automation—>logs—>failureLogs.log. 
2. github-api-automation: For covering different ways, have added the logs in console. 

## Annotations Used:
1. @Test : TestNG annotation for identifying and running tests
2. @BeforeClass, @AfterClass, @BeforeSuit, @AfterSuite : TestNG annotations for running code as per the name suggests.
3. @Step : Allure based annotation for showing the exact step and other details where failures occur. This is shown in the allure reports and saves time in debugging the exact line. This is a method level annotation.

