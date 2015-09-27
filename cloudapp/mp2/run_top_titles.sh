#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/B-output

rm -rf ./build/* ./TopTitles.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TopTitles.java -d build
jar -cvf TopTitles.jar -C build/ ./
hadoop fs -rm -r /mp2/B-output
hadoop jar TopTitles.jar TopTitles  -D stopwords=/mp2/misc/stopwords.txt -D delimiters=/mp2/misc/delimiters.txt -D N=5 /mp2/titles /mp2/B-output

hadoop fs -cat /mp2/B-output/part*
hadoop fs -cat /mp2/B-output/part-r-00000 | sort -n -k2 -r | head -n 100
