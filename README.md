What is a framework?

A framework defines the set of rules/guidelines like coding standards, object repository etc. We can follow these guidelines to achieve the desired results like increase code re-usage, higher portability, reduced script maintenance cost etc.
Keyword Driven Testing Framework:
It is also known as table-driven testing or action word-based testing. In Keyword-driven testing, we use a table format to define keywords or action words for each function or method that we would execute. 
It performs automation test scripts based on the keywords specified in the excel sheet. By using this Framework, testers can work with keywords to develop any test automation script, testers with less programming knowledge would also be able to work on the test scripts. The logic to read keywords and call the required action mentioned in the external excel sheet is placed in the main class. 
In simple words, all the operations and instructions are written in some external file like Excel worksheet. 

Highlights of my Keyword Driven Framework.
1.	Without open any IDE such as eclipse we can create new test scripts and also run the test scripts.
2.	All the test scripts are automatically converted into xml files.
3.	All Locators are converted into xml files from excel.
4.	Single excel file having number of test scripts as we load test scripts from xml file to excel.
5.	Failed test case gives screenshot.
6.	Integrated with extent reporting.
7.	We can integrate with Jenkins CI tool.

Important Component of Keyword Driven Framework.

1. ConfigurationMap folder: - It contains a excel file which is main and starting point of the framework. This file contains below sheets. 
  • Dashboard sheet: - It contains all the information about test cases and summary of the unit testing information of test cases. There are three buttons o Update Test Case button: - we can update the name of test cases are available in our framework. o Run Test Cases button: - we can run our test cases which have Y against their name. o Save Test Cases button: - We can save the running state of the test case which we need to run just put Y against them. By saving the state of running test cases we can run through Jenkins.
  • Test Case sheet: - This sheet contains all the information regarding the testcase. It has four different sections along with one text field int which we enter the testname, with one dropdown which has two options one for create test case and other one for fetch the test case details from xml file. 
      o Events Column: - It has all keyword such as Open Browser, GetUrl, EnterText etc. For common steps we have a new sheet which is Components if we need to call common steps which are written in the components sheet then we need to call components in the given syntax:- Component-{Method name} for example:- I need to call Login method from component sheet then I need to write Component-Login. 
      o Objects Column: - It contains the locator name. To store locator, we have different sheet Objects. But to call any object in the testscripts we need to enter the object name in the test script. 
      o Test Data: - In this column we need to enter the test data or browser name for which we need to open such as chrome /firefox. 
      o WaitTime column: - This column can be used when we need to pause test script at specific amount of time such as 1 sec etc.

  • Object Sheet: - In this sheet we keep al the locators and web element which selenium use. This sheet has three column and Two actions one for save the locators to xml file and second action for fetch all locators from xml file.

  • Components Sheet: - It contains all the steps which are common to 2 or more test scripts. It is the same we create a common method. 
 
All the columns are same which we already discussed in Test Case sheet.

2. Configuration folder: - This folder contains xml files which contains all the details of Component, Execution and Object details which we saved in the excel file.
3. TestScripts folder: - This folder contains all the test cases which we saved in the excel file in the form of xml files.
4. Under Src folder: - You can write your own code for more keywords and business logic.
5. Drivers folder: - You can put your browser drivers such as chrome, ff drivers.
Configuration folder: - This folder contains all the objects components details which we saved in the excel file in the form of xml files.
6. Extent html file: - This is the extent report which give us all the details of passed and failed information.
