#!/bin/bash
mvn clean package
storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartD data.txt > output-part-d.txt
grep Emitting output-part-d.txt | grep "top-words" | tail