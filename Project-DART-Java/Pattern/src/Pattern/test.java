package Pattern;

import java.io.BufferedReader;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test
{

	public static void main(String[] args) throws IOException
	{
		
		 java.io.File mycsv = new java.io.File("C:/Users/AM0C70368/Desktop/output.csv");
		java.io.PrintWriter outfile = new java.io.PrintWriter(mycsv);
		
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/AM0C70368/Desktop/Dart-Java/Project-DART-Java/Pattern/Well/EGFD_WO_1/EGFD_WO_1_DepthLog.csv"));
		String line = null;
		List<String> lines = new ArrayList<>();
		int mnemCount =0;
		String[] mnem_tokens = null;     //store the mnemonics in form of an 1D array 
		
	
		boolean header = true;
		int lineCount = countLine.countLines("C:/Users/AM0C70368/Desktop/Dart-Java/Project-DART-Java/Pattern/Well/EGFD_WO_1/EGFD_WO_1_DepthLog.csv");
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

		
        //int sample = 4;
	    Scanner scan = new Scanner(System.in);
	    System.out.println("");
		System.out.print("Enter how many mneomics are to be selected (Except TIME)  : ");
		int n = Integer.parseInt(scan.nextLine());
		System.out.println("");
		int mnemonics[] = new int[n];
		for (i = 0; i < mnemonics.length; i++)
		{   
			//System.out.print("Enter position of mnemonic (e.g "+ sample+ ":"+ mnem_tokens[sample-1]+") : ");
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
	  double[][] save = new double[rows-5][rows-5];
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
	 
	 
	  double temp;
	 
	  for(i=0 ; i < 13 ; i++)
	  {
		  System.out.println("  ");

		  for(j=0 ; j<13 ; j++)
		  {
			  
			 /* if(save[j][i] == 0)
			  {
				
				  count = j;
				  while(count > 0)
				  {
					  temp = save[count][i];
					  save[count-1][i] = save [count][i];
					  save[count][i] = temp;
					  
					  count--;
					  
				  }
				  
			  }
			  else
			  {*/
				System.out.println(save[j][i]);
			  //}
		  }
	  }
	  
	 /* for(i=0 ; i < 1 ; i++)
	  {
		  for(j=0 ; j<13 ; j++)
		  {
			
		  }
	  }*/
		
	reader.close();
    scan.close();  
    

  }
}


