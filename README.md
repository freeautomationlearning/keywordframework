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
