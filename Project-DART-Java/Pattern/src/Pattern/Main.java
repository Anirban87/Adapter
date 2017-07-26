package Pattern;

import java.io.BufferedReader;
import java.io.FileOutputStream;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		
		
		PrintStream out = new PrintStream(new FileOutputStream("C:/Users/AM0C70368/Desktop/Dart-Java/Project-DART-Java/Pattern/src/output.txt"));
		
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/AM0C70368/Desktop/Dart-Java/Project-DART-Java/Pattern/Well/EGFD_WO_1/EGFD_WO_1_TimeLog.csv"));
		String line = null;
		List<String> lines = new ArrayList<>();
		int mnemCount =0;
		String[] mnem_tokens = null;     //store the mnemonics in form of an 1D array 
		
	
		boolean header = true;
		int lineCount = countLine.countLines("C:/Users/AM0C70368/Desktop/Dart-Java/Project-DART-Java/Pattern/Well/EGFD_WO_1/EGFD_WO_1_TimeLog.csv");
	    Double[][] data = new Double[lineCount][200];
        
    	int i = 0;
    	int j =0;

	   // Generate Two Dimensional Array of Data Values from the CSV -- Start----------------------------------------------------------------------------------------------
    	
    	while ((line = reader.readLine()) != null)
		{
	    	mnemCount = line.length() - line.replace(",", "").length();
		
	    	//System.out.println(mnemCount);   //count the number of mnemonics	    	
	    	if(header == true)
		    {
		    	lines.add(line);
		    	header = false;
		    	System.out.print("Hedders are : ");
		    	System.out.println(lines);
		    	mnem_tokens = line.split(",");
		    } 
		    
		    else
		    {
		    	String [] values = line.split(",");
		    		for (j=0;j<=mnemCount;j++)
		    		{
		    			if(values[j].contains("Z")||values[j].contains("None"))     //in case of other varibales ? exception
		    			{   
		    				//System.out.println("Z");
		    			}
		    			else
		    			{
		    				data[i][j] = Double.parseDouble(values[j]);
		    			
		    			}
		    	    }
		    }
	    	i++;
		}
    	
	    // Generate Two Dimensional Array of Data Values from the CSV -- End----------------------------------------------------------------------------------------

		
        int sample = 4;
	    Scanner scan = new Scanner(System.in);
	    System.out.println("");
		System.out.print("Enter how many mneomics are to be selected (Except TIME)  : ");
		int n = Integer.parseInt(scan.nextLine());
		System.out.println("");
		int mnemonics[] = new int[n];
		for (i = 0; i < mnemonics.length; i++)
		{   
			 System.out.print("Enter position of mnemonic (e.g "+ sample+ ":"+ mnem_tokens[sample-1]+") : ");
			mnemonics[i] = scan.nextInt();
		}

        //matching of mnemonics
		 Double[][] arr = new Double[lineCount][mnemonics.length];
	
	    j=0;
	    int k=0;
	           
	    while(j<mnemCount+1)
		    {
	    		while(k<mnemonics.length)
	    		{
		    		if(j+1 == mnemonics[k])
		    		{
		    				for(i=1;i<lineCount;i++)    
		    				{
		    					arr[i][k] = data[i][j];  //Separating the array for correlation from initial 2d array
		    				}
			         }
		    		
			    	k++;
		         }
	    		j++;
	    		k=0;
		    }   
	 
		int rows = arr.length;
		

 // Chunking the array into blocks of size 5 each calculating correlation and saving as a 2d array-------------------------------------------------------------
	  
	  int x=0,y=0;
	  double[][] save = new double[rows][rows];
	  double[] subarr1 = new double[5];
	  double[] subarr2 = new double[5];
	  Correlation obj = new Correlation();
	  double rec; 
	 
	  int iter = 0;
	  
	  int start = 1;
	  int end = 5;
 
	  int count = 1 ;

	  while(count < rows -5)
	  {    
		    k=0;
		 
	  		for(i = start ; i<=end ;i++)
	  		{
	  					subarr1[k] = arr[i][0];
	  					k++;
	  		}
	  		start++;
	  		end++;
	  		iter = 0;
	  		
	  		while(end<rows)
	  		{
	  			
	  			k=0;
	  			for(j= start; j<=end ;j++)
	  			{
	  					subarr2[k] = arr[j][0];
	  					k++;		
	  			}
		  
	  			rec=obj.corr(subarr1, subarr2);
	  			save[x][y]=rec;
	  			x++;
	  			iter = iter + 1;
	  			start++;
	  			end++;
	  		
			}  
	  		
	        x = x-iter; 
	  		start = start - iter; 
			end = end - iter;
	  	    count++;
	  	    y++;
	  	   
	  }
	  
	// arranging the 2d array in the lower diagonal--------------------------------------------------------------------------------
	 
	  //System.out.println(save[5][0]);
	  int temp_count =0;
	  double temp;
	 
	  for(i=0 ; i <rows ; i++)
	  {
		
		  for(j=0 ; j<rows ; j++)
		  {
			
			  if(save[j][i] == 0.0)
			  {
				  temp_count = j;
				  while(temp_count > 0)
				  {
					  temp = save[temp_count-1][i];
					  save[temp_count-1][i] = save[temp_count][i];
					  save[temp_count][i] =temp;
					  temp_count--;
				  }
				  
			  }
			  
			  else
			  {
				continue;
			  } 
		  }
	  }
	  
	 for(i=0 ; i<rows-5; i++)
	  {
		 System.out.println(" ");
		 
		  for(j=0 ; j<rows-5; j++)
		  {
			
			out.println(save[j][i]);
			System.out()
			
			if(i==j)
			{
				System.out.println("1");
				
			}
			else if(save[j][i] == 0.0)
			{
				continue;
			}
			
			else
			{
			  System.out.println(save[j][i]);
			  
			}
			
		  }
	  }
		
	reader.close();
    scan.close();  
    out.close();

  }
}


