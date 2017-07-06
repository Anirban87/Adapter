package Adapter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.*;
import java.net.URI;

public class Push
{
		public void Push_HDFS() throws IOException 
		{
			String localSrc = "   				";								//Source file in the local file system (CSV file)
			String dst = "  				    ";								//Destination file in HDFS
			
			InputStream in = new BufferedInputStream(new FileInputStream(localSrc));	
		
			Configuration conf = new Configuration();									//Get configuration of Hadoop system
			System.out.println("Connecting to -- "+conf.get("fs.defaultFS"));
		
			FileSystem fs = FileSystem.get(URI.create(dst), conf);						//Destination file in HDFS
			OutputStream out = fs.create(new Path(dst));
		
			IOUtils.copyBytes(in, out, 4096, true);										//Copy file from local to HDFS
			 
			System.out.println(dst + " copied to HDFS");
			
		}
}

