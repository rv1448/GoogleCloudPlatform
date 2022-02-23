package com.gcp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;

import util.Tokenize;

public class FirstPipeline  {

    public static void main  (String[] args) {
  
 

        List<String> lines = new ArrayList<String>();

        try {
            lines = Files.readAllLines(Paths.get("/Users/rahulvangala/GoogleCloudPlatform/dataflow/lorem.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
         
            e.printStackTrace();
        }
        System.out.println(lines.get(0)); 
        Pipeline pipeline = Pipeline.create();
       
        pipeline.apply(Create.of(lines))
        .apply(Tokenize.of()) 
        .apply(Count.perElement())
        .apply(util.PrintElements.of());
        


        
        
        pipeline.run().waitUntilFinish();


    }
    
}
