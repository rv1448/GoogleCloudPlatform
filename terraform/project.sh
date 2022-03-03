#!/bin/sh


gcpproject=$(cat ./secrets.json | jq -r .project_id)
echo "{\"output\":\"$gcpproject\"}"