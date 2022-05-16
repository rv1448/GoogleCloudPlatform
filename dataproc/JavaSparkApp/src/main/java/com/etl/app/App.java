package com.etl.app;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {


    public static Dataset<Row> renamecolumns(Dataset<Row> df)
    {
        List<String> old_columns = Arrays.asList(df.columns());
        List<String> modified_columns = old_columns.
                stream().
                map(x -> x.toLowerCase().trim().
                        replaceAll("([^a-zA-Z0-9]|\\s)+", "_"))
                .map(s -> s.endsWith("_") ? s.substring(0,s.length()-1) : s)
                .collect(Collectors.toList());

        for (int i =0; i < old_columns.size(); i++){
            df = df.withColumnRenamed(old_columns.get(i), modified_columns.get(i));
        }

        return  df;
    }

    public static void main(String[] args) {
        if (args.length != 3 ){
            throw new IllegalArgumentException("Exactly 2 arguements are required");
        }


        String inputPath = args[0];
        String outputPath = args[1];
        String clusterMode = args[2];


        SparkSession spark = SparkSession.builder()
                .appName("csv ingestion")
                .master(clusterMode)
                .getOrCreate();

       Dataset<Row> df =  spark.read()
                .format("csv")
                .option("header","true")
                .load(inputPath);

       Dataset<Row> df_modified = renamecolumns(df);
       df_modified.coalesce(1).write()
               .csv(outputPath);




    }
}