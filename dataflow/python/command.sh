python3 -m apache_beam.examples.wordcount \
--project=playground-s-11-263ffd42 \
--runnner=DataflowRunner \
--temp_location=gs://hands-on-data-flow/output \ 
--output=gs://hands-on-data-flow/ \
--job_name=test \
--region=us-central1 


python3 -m apache_beam.examples.wordcount \
--input gs://dataflow-samples/shakespeare/kinglear.txt \
--output gs://hands-on-data-flow-acloud/counts \
--runner DirectRunner \
--project playground-s-11-263ffd42 \
--region us-central1  \
--temp_location gs://hands-on-data-flow-acloud/tmp/ \
--service_account_email 338484174494-compute@developer.gserviceaccount.com