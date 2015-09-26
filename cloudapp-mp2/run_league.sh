#!/bin/bash
hadoop fs -mkdir -p /mp2/A-input
hadoop fs -mkdir -p /mp2/F-output

rm -rf ./build/* ./PopularityLeague.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main PopularityLeague.java -d build
jar -cvf PopularityLeague.jar -C build/ ./
hadoop fs -rm -r /mp2/F-output
hadoop jar PopularityLeague.jar PopularityLeague -D league=/mp2/misc/league.txt /mp2/links /mp2/F-output

hadoop fs -cat /mp2/F-output/part*
hadoop fs -cat /mp2/F-output/part-r-00000 | head -n 15
