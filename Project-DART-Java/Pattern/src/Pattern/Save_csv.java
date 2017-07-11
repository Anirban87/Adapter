package Pattern;

import java.io.IOException;

public class Save_csv {
	
	public void save(double[] arr) throws IOException 
	{

    java.io.File courseCSV = new java.io.File("C://Users/AM0C70368/Downloads/Project-DART-Java-master/Project-DART-Java/Pattern/src/Pattern/output.csv");

    java.io.PrintWriter outfile = new java.io.PrintWriter(courseCSV);

    for (int i=0; i < arr.length ; i++)
    {
        outfile.write((int) arr[i]);
    }

    outfile.close();
	} 
}	 




    
