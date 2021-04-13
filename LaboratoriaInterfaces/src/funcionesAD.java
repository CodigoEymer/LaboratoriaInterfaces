

public class funcionesAD {
	static int lonLocal;
	static double[] vector;

	public funcionesAD(int longitud) {
		// TODO Auto-generated constructor stub
		lonLocal=longitud;
	}
	
	static double[] lineal(double m,double b) {
		vector = new double[lonLocal];
		
		for(int i=0;i<lonLocal;i++)
        {
			vector[i] = m*i+b;
        }
		return vector;
	}
	
	static double[] cuadratica() {

		double a = 1;
		double b = 2;
		double c = 4;
		
		vector = new double[lonLocal];		
		
		for(int i=0;i<lonLocal;i++)
        {
			vector[i] =((double)Math.pow(i,2)*a)+i*b+c;
        }
		return vector;
	}
	
	static double[] seno() {
		
		vector = new double[lonLocal];
		
		double r = (Math.PI)/lonLocal;
		
		double j = 0;
		for(int i=0;i<lonLocal;i++)
        {
			j = j + r;
			vector[i] = Math.sin(j);
        }
		return vector;
	}
	
	  static double[] digital(int a,int b) {
		  
		  vector = new double[lonLocal];
		  int k=0;
		  while(k<lonLocal) {
				
				for(int j=0;j<a;j++) {
					if(k<lonLocal) {
						vector[k]=5;
					}
					k++;
				}
				
				for(int c=0;c<b;c++) {
					if(k<lonLocal) {
						vector[k]=1;
					}
					k++;
				}
			}
		  return vector;
	  }
	 
}
