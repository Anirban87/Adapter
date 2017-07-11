
package Pattern;

public class Test
{
	//public double corr(double[] arr1, double[] arr2)
	public static void main(String[] args)
		{
		
		double covar = 0.0;
		
		double x1[] = {419.3592,
				419.384,
				419.384,
				419.4022,
				419.4022};

		double x2[] = {436.8433,
				436.8572,
				436.8636,
				436.8763,
				436.8763};
	
		double[] diff_x1 = new double[5];
		double[] diff_x2 = new double[5];

		int x1_len = x1.length;
		int x2_len = x2.length;

		double sum_x1  = 0.0;
		double sum_x2  = 0.0;

		for(int i=0; i< 5 ; i++ )
		{
		sum_x1 = sum_x1 + x1[i];
		sum_x2 = sum_x2 + x2[i];
		}

		double x1_mean = (sum_x1 / x1_len);
		double x2_mean = (sum_x2 / x2_len);

		for(int j =0 ; j <5 ; j++)
		{
		diff_x1[j] = x1[j] - x1_mean;
		diff_x2[j] = x2[j] - x2_mean;
		}

		double[][] Prod_Diff_X1_X2 = new double[x1.length][x2.length];

		for(int i = 0; i < x1.length; i++)
		{
			for(int j = 0; j < x2.length; j++)
			{
				Prod_Diff_X1_X2[i][j] = diff_x1[i] * diff_x2[j];

			}
		}

		double Sum_Prod_Diff = 0.0;
		int Prod_Diff_X1_X2_length = Prod_Diff_X1_X2.length;
		

		for(int i = 0; i <Prod_Diff_X1_X2_length ; i++)
		{
			for(int j =0 ;j< x2.length ; j++)
			{
				Sum_Prod_Diff = Sum_Prod_Diff + Prod_Diff_X1_X2[i][j];
				
			}
		}

		covar = Sum_Prod_Diff/ (x1_len - 1);							//covaraince calculated here

		//calculation of standard deviation--------------------------------------------------------------------------------------------

		//deviation of the first array

		double[] Sqr_DiffX1 = new double[5];
		for(int i=0 ; i<Sqr_DiffX1.length ; i++)
		{
		Sqr_DiffX1[i] = diff_x1[i] * diff_x1[i];

		}

		double Sum_sqr_diffx1 = 0.0;
		for(int i =0 ; i <Sqr_DiffX1.length ; i++)
		{
		Sum_sqr_diffx1 = Sum_sqr_diffx1 + Sqr_DiffX1[i];
		}

		double SD_X1 = 0.0;
		SD_X1 =  (Sum_sqr_diffx1 / (((x1.length)-1)^(1/2)));

		//deviation of the second array

		double[] Sqr_DiffX2 = new double[5];
		for(int i=0 ; i<Sqr_DiffX2.length ; i++)
		{
		Sqr_DiffX2[i] = diff_x2[i] * diff_x2[i];

		}

		double Sum_sqr_diffx2 = 0.0;
		for(int i =0 ; i <Sqr_DiffX2.length ; i++)
		{
		Sum_sqr_diffx1 = Sum_sqr_diffx1 + Sqr_DiffX1[i];
		}

		double SD_X2 = 0.0;
		SD_X2 =  (Sum_sqr_diffx2 / (((x2.length)-1)^(1/2)));

		//calculation of coovariance---------------------------------------------------------------------------------------------------

		double Corel = 0.0;

		Corel = covar/(SD_X1*SD_X2);

		//return (Corel);
		System.out.println(Corel);

		}
}
