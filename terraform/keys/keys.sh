#!/bin/bash
PROJECT=`gcloud config list --format 'value(core.project)'`
USER_EMAIL=`gcloud config list account --format "value(core.account)"`
REGION=us-central1


keys=$(ls -lt /Users/rahulvangala/Downloads/playground*.json | head -n 1 | awk  '{print $9}')
echo $keys
cp $keys ./secrets.json
gcpproject=$(cat ./secrets.json | jq -r .project_id)
echo $gcpproject
gcloud auth revoke
gcloud auth activate-service-account --key-file=./secrets.json --project=$gcpproject
gcloud config set project $gcpproject
export project=$gcpproject


# USER_EMAIL=`gcloud config list account --format "value(core.account)"`

# gcloud projects get-iam-policy $PROJECT  \
# --format='table(bindings.role)' \
# --flatten="bindings[].members" \
# --filter="bindings.members:$USER_EMAIL"