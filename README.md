# RPN-Calculator

## Introduction
This project is a Reverse Polish Notation(RPN) calculator. It accepts a string with commands to execute and display the output.

## Requirements

- The calculator has a stack that can contain real numbers.

- The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers and operators.

- Numbers are pushed on to the stack. Operators operate on numbers that are on the stack. 

- Available operators are +, -, *, /, sqrt, undo, clear.

- Operators pop their parameters off the stack, and push their results back onto the stack.

- The ‘clear’ operator removes all items from the stack.

- The ‘undo’ operator undoes the previous operation. “undo undo” will undo the previo us two operations.

- sqrt performs a square root on the top item from the stack.

- The ‘+’, ‘-’, ‘*’, ‘/’ operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack.

- After processing an input string, the calculator displays the current contents of the stack as a space-separated list.

- Numbers should be stored on the stack to at least 15 decimal places of precision, but displayed to 10 decimal places (or less if it causes no loss of precision).

- All numbers should be formatted as plain decimal strings (ie. no engineering formatting).

- If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
operator <operator> (position: <pos>): insufficient parameters
  
- After displaying the warning, all further processing of the string terminates and the current state of the stack is displayed.

## Attention
- 'undo' operations can work on an empty stack. It will do nothing.
- 'undo' opeartions can used to undo 'clear' opeartion.
  
  For example:
  
  ```Java
  5 2
  stack: 5 2
  clear
  stack:
  undo
  stack:5 2
  ```
## Extension
- More math operators, such as n!, COS, ATAN, etc
  - Solution:
    - Implements your own class that implments 'Operator' interface
    - Add your operator into 'OperatorTypeEnums' enum
    - Define your error code into 'ErrorCodeEnum' enum
  
- Customize the colour of each number in the stack depends on it position.
  - Solution:
    - Add the extended properties and functions into 'ValueElement' class
  
## Compile, Test, Run and Packaging

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn exec:java`

- Packaging: `mvn package`, compiled jar at *target/rpn-calculator.jar

## Script Files

- run_build.sh: clean, compile and run unit testing

- run_deploy: clean, compile, run unit testing and deploy the jar file to a local folder

## Author
Shen Xiang
E-mail: [aries_shen@163.com](mailto:aries_shen@hotmail.com)
