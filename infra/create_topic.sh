#!/bin/sh


kubectl exec -t kafka-0 -- /opt/kafka/bin/kafka-topics.sh "$@"