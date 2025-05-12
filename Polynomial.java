public class Polynomial {

double[] coefficients;

public Polynomial()
{
	coefficients = new double[1];
}

public Polynomial(double[] coefficients){

	this.coefficients = coefficients.clone();
}

public Polynomial add(Polynomial p) {
	
	double[] big;
	double[] small;
	if(p.coefficients.length>coefficients.length) {
		big = p.coefficients; small = this.coefficients;
	
	} else {
		small = p.coefficients; big = this.coefficients;
		
	}
	Polynomial result = new Polynomial(big);
	for(int i = 0; i < small.length; i++) {
		
		result.coefficients[i] += small[i];
		
	}
/* 	for(int j = 0; j < big.length; j++) {

		System.out.println(result.coefficients[j]);
	}*/
	return result;
}

public double evaluate(double in) {
	double res = 0;
	for(int i =0; i < coefficients.length; i ++) {
		
		res += Math.pow(in, i) * coefficients[i];
	}
	 return res;
}
public boolean hasRoot(double in) {
	
	return evaluate(in) == 0;

}


}