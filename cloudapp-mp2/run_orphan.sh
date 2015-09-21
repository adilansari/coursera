#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/D-output

rm -rf ./build/* ./OrphanPages.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main OrphanPages.java -d build
jar -cvf OrphanPages.jar -C build/ ./
hadoop fs -rm -r /mp2/D-output
hadoop jar OrphanPages.jar OrphanPages /mp2/links /mp2/D-output

hadoop fs -cat /mp2/D-output/part*
hadoop fs -cat /mp2/D-output/part-r-00000 | sort -n -k2 -r | head -n 100
