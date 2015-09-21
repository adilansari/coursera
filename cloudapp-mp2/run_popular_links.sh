#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/D-output

rm -rf ./build/* ./TopPopularLinks.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TopPopularLinks.java -d build
jar -cvf TopPopularLinks.jar -C build/ ./
hadoop fs -rm -r /mp2/D-output
hadoop jar TopPopularLinks.jar TopPopularLinks -D N=5 /mp2/links /mp2/D-output

hadoop fs -cat /mp2/D-output/part*
hadoop fs -cat /mp2/D-output/part-r-00000 | sort -n -k2 | head -n 10
