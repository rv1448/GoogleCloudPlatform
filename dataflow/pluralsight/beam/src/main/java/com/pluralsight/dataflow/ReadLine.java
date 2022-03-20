package com.pluralsight.dataflow;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.TupleTag;

public class ReadLine {
    
interface Myoptions extends PipelineOptions {
    @Description("Give the input path to the text file")
    @Default.String("/tmp/input.txt")
    String getInput();
    void setInput(String input);
 
}


public static void main(String[] args) {
    PipelineOptionsFactory.register(Myoptions.class);
    PipelineOptions po = PipelineOptionsFactory.fromArgs(args).withValidation().as(Myoptions.class);
    Pipeline pp = Pipeline.create(po);
    

    }


}
 