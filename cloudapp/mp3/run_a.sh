#!/bin/bash
mvn clean package
storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartA > output-part-a.txt