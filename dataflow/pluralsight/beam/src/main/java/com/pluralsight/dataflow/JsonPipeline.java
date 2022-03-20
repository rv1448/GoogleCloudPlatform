package com.pluralsight.dataflow;

import com.google.gson.Gson;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonPipeline {
    private static final Logger LOG = LoggerFactory.getLogger(JsonPipeline.class);
    public interface Options extends DataflowPipelineOptions {

    }


    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).as(Options.class);
        run(options);
    }

    @DefaultSchema(JavaFieldSchema.class)
    public static class CommonLog {
        String user_id;
        String ip;
        Double lat;
        Double lng;
        String timestamp;
        String http_request;
        String user_agent;
        Long http_response;
        Long num_bytes;
    }

    static class JsonToCommonLog extends DoFn<String, CommonLog> {

        @ProcessElement
        public void processElement(@Element String json, OutputReceiver<CommonLog> r) throws Exception {
            Gson gson = new Gson();
            CommonLog commonLog = gson.fromJson(json, CommonLog.class);
            r.output(commonLog);
        }
    }
    
    public static PipelineResult run(Options options) {

        // Create the pipeline
        Pipeline pipeline = Pipeline.create(options);
        options.setJobName("my-pipeline-" + System.currentTimeMillis());

        // Static input and output
        String input = "gs://qwiklabs-gcp-00-1e283968f3fd/events.json";
        String output = "qwiklabs-gcp-00-1e283968f3fd:logs.logs";

        /*
         * Steps:
         * 1) Read something
         * 2) Transform something
         * 3) Write something
         */

        pipeline.apply("ReadFromGCS", TextIO.read().from(input))
                .apply("ParseJson", ParDo.of(new JsonToCommonLog()));
                // .apply("WriteToBQ",
                //         BigQueryIO.<CommonLog>write().to(output).useBeamSchema()
                //                 .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_TRUNCATE)
                //                 .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED));
        LOG.info("Building pipeline...");

        return pipeline.run();
    }
}

// // mvn compile exec:java -Dexec.mainClass=com.pluralsight.dataflow.JsonPipeline \
// // -Dexec.args="--gcpTempLocation=gs://qwiklabs-gcp-00-1e283968f3fd/
// --gcpTempLocation=gs://qwiklabs-gcp-00-1e283968f3fd/"