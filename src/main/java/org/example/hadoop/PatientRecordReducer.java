package org.example.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PatientRecordReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // Implement aggregation logic from PatientRecordClassifier.java
        StringBuilder combinedValues = new StringBuilder();
        for (Text value : values) {
            combinedValues.append(value.toString()).append(",");
        }
        context.write(key, new Text(combinedValues.toString()));
    }
}
