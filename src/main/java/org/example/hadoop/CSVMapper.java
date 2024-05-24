package org.example.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.naming.Context;
import java.io.IOException;

public class CSVMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Implement logic from CSVReader.java
        String line = value.toString();
        String[] fields = line.split(",");

        // Assuming fields[0] is some key and fields[1] is some value
        context.write(new Text(fields[0]), new Text(fields[1]));
    }
}
