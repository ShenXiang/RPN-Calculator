#!/bin/bash

if [ -z $1 ]; then
    echo "$1"
    echo "$0 target-folder"
    echo "Error, target is empty"
    exit -1
fi

mvn clean compile test package

cp target/rpn-calculator.jar $1
