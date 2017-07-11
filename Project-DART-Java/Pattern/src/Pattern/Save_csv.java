package Pattern;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save_csv {
	
	public void save(double[] arr) throws IOException 
	{
		String myfile = "C://Users/AM0C70368/Downloads/Project-DART-Java-master/Project-DART-Java/Pattern/src/Pattern/output.csv";
		BufferedWriter br = new BufferedWriter(new FileWriter(myfile));
		for (int j = 0; j < arr.length; j++) 
		{
		    char str = ((char) arr[j]);
		    br.append("\n");
		    br.write(str);
		}
		br.close();
	
	}
	 
}
