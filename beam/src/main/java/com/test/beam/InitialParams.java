package com.test.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;
import java.util.List;

public class InitialParams {


    public static void main(String[] args) {
        PipelineOptions po =  PipelineOptionsFactory.create();
        Pipeline pp = Pipeline.create(po);


        PCollection<String> collection = pp.apply(TextIO.read().from("src/main/resources/wc.txt"));
        PCollection<List<String>> collection1 = collection.apply("SplitString", ParDo.of(new ComputeWordLength()));
        collection1.apply( FlatMapElements.into(TypeDescriptors.strings()));
        pp.run().waitUntilFinish();
    }



    static class ComputeWordLength extends DoFn<String, List<String>>{

        @ProcessElement
        public void procesElement(@Element String word, OutputReceiver<List<String>> out){
            out.output(Arrays.asList(word.split(" ")));
        }

    }







}
