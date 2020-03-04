#!/bin/bash

rm -rf build
mkdir build
javac -d build $(find . -name '*.java')
java -cp build recocido.Main
