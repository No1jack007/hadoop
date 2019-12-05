package com.zhang.hadoop.service.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by zhang yufei on 2018/9/6.
 */
public class WordCountDriver {

    public static void main(String [] args) throws Exception{
        Configuration configuration=new Configuration();
//        configuration.set("mapreduce.frameword.name","yarn");
//        configuration.set("yarn.resoucemanager.hostname","hadoop1");
        Job job= Job.getInstance(configuration);

        //指定本程序的jat包所在的本地路径
        job.setJarByClass(WordCountDriver.class);

        //指定本业务job要使用的mapper/reducer业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //指定maopper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //指定job的输出结果所在目录
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //将job中配置的相关参数，以及job所用的java累所在的jar包，提交给yarn去运行
        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
