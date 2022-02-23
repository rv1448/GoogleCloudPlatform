#!/bin/sh 


kubectl exec -t kafka-0 -- /opt/kafka/bin/kafka-console-consumer.sh "$@" --bootstrap-server kafka:9093