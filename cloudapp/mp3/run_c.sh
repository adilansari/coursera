#!/bin/bash
mvn clean package
storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartC data.txt > output-part-c.txt
grep Emitting output-part-c.txt | grep "count default" | tail