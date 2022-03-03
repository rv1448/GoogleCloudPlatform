#!/bin/bash
keys=$(ls -lt /Users/rahulvangala/Downloads/playground*.json | head -n 1 | awk  '{print $9}')
echo $keys
cp $keys ./secrets.json
gcpproject=$(cat ./secrets.json | jq -r .project_id)
echo $gcpproject
gcloud auth revoke
gcloud auth activate-service-account --key-file=./secrets.json --project=$gcpproject
gcloud config set project $gcpproject