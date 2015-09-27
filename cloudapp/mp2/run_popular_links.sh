#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/E-output

rm -rf ./build/* ./TopPopularLinks.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TopPopularLinks.java -d build
jar -cvf TopPopularLinks.jar -C build/ ./
hadoop fs -rm -r /mp2/E-output
hadoop jar TopPopularLinks.jar TopPopularLinks -D N=5 /mp2/links /mp2/E-output

hadoop fs -cat /mp2/E-output/part*
hadoop fs -cat /mp2/E-output/part-r-00000 | head -n 15
