package Adapter;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException
	{
		//creation of csv 
		Create_CSV file = new Create_CSV();
		Create_CSV.Create_data();						//generate csv for data
		Create_CSV.Create_log();						//generate csv for log
		
		
		
		//pushing the csv into HDFS
	}
}
