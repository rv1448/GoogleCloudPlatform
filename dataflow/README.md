# dataflow


#### MAIN CONCEPTS
- Controller service account that compute engine vm use for running the dataflow can be changed using
  - Flag to Override default controller(default service account). This should atleast include df worker role
    - Python: __--service_account_email__ 
    - Java: __--serviceAccount__
- Maven create a blanck project
- 
  ``` sh
  mvn archetype:generate -DgroupId=com.pluralsight.dataflow \
  -DartifactId=beam \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false


  mvn clean dependency:resolve
  mvn dependency:sources dependency:resolve -Dclassifier=javadoc

  export BEAM_VERSION=2.37.0
  export JAVA_VERSION=11


  mvn archetype:generate \
      -DinteractiveMode=false \
      -DarchetypeGroupId=org.apache.beam \
      -DarchetypeArtifactId=beam-sdks-java-maven-archetypes-starter \
      -DarchetypeVersion=$BEAM_VERSION \
      -DtargetPlatform=$JAVA_VERSION \
      -DartifactId=check-pipeline-dependencies \
      -DgroupId=org.apache.beam.samples
    
  mvn dependency:resolve && mvn -o dependency:list

  ```

#### APACHE BEAM = Batch + Stream
 - 4 mains concepts
   - PCOLLECTION
     - Distributed data collection
     - Immutable
   - PTRANSFORM
     - Input and output transformations
   - PIPELINE
   - PIPELINE RUNNER
     - Backend system that runs the pipeline
 - Utility Transforms
 - ParDo
   - 
### BEAM CONCEPTS
- Processing time | Event time
  -  Dataflow assigns timestamp to every message - Processing time 
  -  Timestamp at which the message originated - Event time
- Windows
  - Fixed 
  - sliding
  - Session
- WaterMarks
  - When do windows emit the results
  - Late data. 




#### Pipeline
- A pipeline is a directed acyclic graph of data transformations applied one or more collection of data. Ptransforms can read and output PCollections. 
- Elements in the PCOllection have schema associated with them. 
- No random selection for the PCollection

#### Dataflow IAM 

- When an apache beam user code is submitted. Files are sent to GCS and Dataflow Service. Validates 
- The roles are needed to have required authentication
  - User roles
  - DataFlow Service account  
    - Dataflow Service account interacts between your project and dataflow  
    - Used for worker creation and monitoring
    - Service Account: __service-<project_number>@dataflow-service-producer-prod.iam.gserviceaccount.com__
    - This account is created when Dataflow api is enabled. 
    - This account is assigned the Dataflow service agent role
  - Controller Service account
    - Controller service account is assigned to the VM's that run the pipeline. 
    - Used by the workers to access resources needed by the pipeline
    - By default projects compute engine default service account
    - Service Account: __<project-number>-compute@developer.gserviceaccount.com__
    - flag to override controller service account default. The custom service account should have DF worker role atleast. 
      - Python: __--service_account_email__
      - Java: __--serviceAccount__


- Roles 
  - Dataflow Viewer role
    - provides read-only access to all dataflow related sources 
  - Dataflow Developer
    - Managing pipelines
    - cannot Submit, Stage files, view Compute engine quota
    - Provide access to view, update and cancel dataflow jobs
  - DataFlow Admin roles 
  



``` python
python3 -m apache_beam.examples.wordcount \
--input gs://dataflow-samples/shakespeare/kinglear.txt \
--output gs://hands-on-data-flow/counts \
--runner DataflowRunner \
--project playground-s-11-263ffd42 \
--region us-central1  \
--temp_location gs://hands-on-data-flow/tmp/ \
--service_account_email 703461526458-compute@developer.gserviceaccount.com




python3 -m apache_beam.examples.wordcount \
--input gs://dataflow-samples/shakespeare/kinglear.txt \
--output gs://hands-on-data-flow-acloud/counts \
--runner DataflowRunner \
--project playground-s-11-68d8e670 \
--region us-central1  \
--temp_location gs://hands-on-data-flow-acloud/tmp/ \
--experiments=shuffle_mode=service


```



