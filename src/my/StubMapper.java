package my;
import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

   public class StubMapper extends Mapper<Object, Text, Text, Text> {
	   
  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
	  
     /*
     * TODO implement
     */
	  
	  String[] words = value.toString().split("[ \t]+");
	  
	  String User = words[0];
	  
	  String DNA = words[1];
	  
	  context.write(new Text(DNA), new Text(User));
	  
  }
}

