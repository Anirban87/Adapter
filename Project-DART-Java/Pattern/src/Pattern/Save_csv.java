package Pattern;

import java.io.FileWriter;
import com.csvreader.CsvWriter;

public class Save_csv {
	
	public void save(double[] arr) 
	{
	String csv = "C://Users/AM0C70368/Downloads/Project-DART-Java-master/Project-DART-Java/Pattern/src/output.csv";
	CsvWriter writer = new CsvWriter(new FileWriter(csv));

	for(int i =0; i< arr.length ;i++)
	{
		String [] country = ;  //save the menonics in a column 
	}
	writer.write(country);

	writer.close();
	}
	 
}
