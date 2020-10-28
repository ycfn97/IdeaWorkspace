package myjoin.mymapjoin;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Map<String,String> map=new HashMap<>();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits=value.toString().split("\t");
        String name=map.get(splits[1]);
        context.write(new Text(splits[0]+"\t"+name+"\t"+splits[2]),NullWritable.get());
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        Path path = new Path(cacheFiles[0]);
        FileSystem fileSystem = FileSystem.get(context.getConfiguration());
        FSDataInputStream open = fileSystem.open(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, "utf-8"));
        String line;
        while (StringUtils.isNotEmpty(line=bufferedReader.readLine())){
            String [] splits=line.split("\t");
            map.put(splits[0],splits[1]);
        }
        IOUtils.closeStream(bufferedReader);
    }
}
