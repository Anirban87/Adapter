
package Adapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Log_Csv 
{
	public void create_log_csv() throws FileNotFoundException
	{
		    String csvFile = "C:/Users/AM0C70368/Desktop/output_log.csv";
		    BufferedReader br = null;
		    String line = " ";
		    String cvsSplitBy = ",";
		    
		    PrintStream out = new PrintStream(new FileOutputStream("C:/Users/AM0C70368/Desktop/sample_log.csv"));
		    try 
		    {
		        br = new BufferedReader(new FileReader(csvFile));
		        while ((line = br.readLine()) != null) {
		        String[] csvRead = line.split(cvsSplitBy);
		        for(int i=0;i<38;i++)
		        {
		        System.out.print(csvRead[i].replace("\"","").replace("{","").replace("}","").replace("mnemonic","").replace(":","")+",");
		        System.setOut(out);
		   
		        }

		    }
		    }
		    catch (FileNotFoundException e)
		    {		        e.printStackTrace();     } 
		    
		    catch (IOException e)
		    {
		        e.printStackTrace();                 }
		    
		    finally {
		        if (br != null) {
		            try {
		                br.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		  
}
}
