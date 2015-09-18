#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/A-output

rm -rf ./build/* ./TitleCount.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TitleCount.java -d build
jar -cvf TitleCount.jar -C build/ ./
hadoop fs -rm -r /mp2/A-output
hadoop jar TitleCount.jar TitleCount -D stopwords=/mp2/misc/stopwords.txt -D delimiters=/mp2/misc/delimiters.txt /mp2/titles /mp2/A-output

hadoop fs -cat /mp2/A-output/part*
