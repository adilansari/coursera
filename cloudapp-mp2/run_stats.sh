#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/C-output

rm -rf ./build/* ./TopTitleStatistics.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TopTitleStatistics.java -d build
jar -cvf TopTitleStatistics.jar -C build/ ./
hadoop fs -rm -r /mp2/C-output
hadoop jar TopTitleStatistics.jar TopTitleStatistics  -D stopwords=/mp2/misc/stopwords.txt -D delimiters=/mp2/misc/delimiters.txt /mp2/titles /mp2/C-output

hadoop fs -cat /mp2/C-output/part*
hadoop fs -cat /mp2/C-output/part-r-00000 | sort -n -k2 -r | head -n 100
