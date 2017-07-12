package Pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Casting
{

	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("C://Users/AM0C70368/python_projects/CSV/Well/EGFD_WO_1/EGFD_WO_1_TimeLog.csv"));
		String line = null;
		List<String> lines = new ArrayList<>();
		int mnemCount =0;
		String[] mnem_tokens =null;     //store the mnemonics in form of an 1D array 
		
	
		boolean header = true;
	
	    int lineCount = countLine.countLines("C://Users/AM0C70368/python_projects/CSV/Well/EGFD_WO_1/EGFD_WO_1_TimeLog.csv");
		    
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
	   // int cols = arr[0].length;

	    // Chunking the array into blocks of size 5 each---------------------------------------------------------------------------------
	
	    double[] subarr1 = new double[5];                        
	    double[] subarr2 = new double[5];	 
	    double[] save_mnemo = new double[3000];
	    double rec;
    
	    Correlation obj = new Correlation();
	    Save_csv send = new Save_csv();
	
	    int start = 1;
	    int end = 5;
	    int begin = 1;
	    k=0;
	    int iter = 1;
	    
	   int count = 1;
	    
	   while(count <end -1 )
	    {   
	    	
	    	if(begin ==1)
	    	{ 
	       		for(j=start;j<=end;j++)           			//modify the j with start and till end
	    		{
	    			subarr1[k]=arr[j][i];
	    			System.out.println(subarr1[k]);
	    			k++;
	    			iter =iter +1;
	    		}
	       		
	       		begin++;
	    	}
	    	 
	    	else
	    	{   
	    		 while(end<rows)
	    		 {
	    			
	    	        System.out.println(" ");
	    			for(j=start;j<=end;j++)
	    			{
	    				for(k=0;k<5;k++)
	    				{
	    					subarr2[k]=arr[j][i-1];
	    					System.out.println(subarr2[k]);
	    					j++;
	    				}
	    				iter =iter +1 ;
	    				rec=obj.corr(subarr1, subarr2);
	    				save_mnemo[start] = rec;
	    				System.out.println("\n Correlation coefficient is for iteration  "+start+ " is : " +save_mnemo[start]);
	    			    end=j;
	    				start = j-4;	
	    			}
	    		 }
	    	 }
	    	count++;
	    }
	    
   send.save(save_mnemo);
   reader.close();
   scan.close();
  // list_Scan.close();
  }
}



