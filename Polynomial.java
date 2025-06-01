import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
public class Polynomial {

double[] coefficients;
int[] powers;

public Polynomial()
{
	coefficients = new double[1];
	powers = new  int[1];
}
public Polynomial(String filename) {
	File file = null;
	Scanner scan = null;
	try{
  file = new File(filename);
  scan = new Scanner(file);
	}
	catch (FileNotFoundException e) {
		System.out.println("Oh no your file doesn't exist or isn't in the directory :( :( :(");
		return;
	}
	String line = scan.nextLine();
//	System.out.println(line);
	int arraySize = 1;
	int width = line.length();
	for(int i = 0; i < line.length(); i++) {
		char c = line.charAt(i);
		if(c == 'x'){
			arraySize++;
			
		}	
		if(c == '-'|| c=='+')  {
			line = line.substring(0, i) + "," + line.substring(i, line.length());
			i++;
		}
	}
	this.powers = new int[arraySize];
	this.coefficients = new double[arraySize];
	String[] bits = line.split(",");
	boolean isCo = true;
	int iter = 0;
	//System.out.println(line);
	//System.out.println("size of " + arraySize);
	for(String str : bits) {
		int index = ExZero(str);
		//System.out.println(str);
		//System.out.println(index);
		if(index >= 0 ){
			
			coefficients[iter] = Double.parseDouble(str.substring(0,index));
			powers[iter] = Integer.parseInt(str.substring(index+1));
		}
		else {
			powers[iter] = 0;
			coefficients[iter] = Double.parseDouble(str);

		}
		iter ++;
	}

}
int ExZero(String str)
{
	for(int i =0; i < str.length();i++) {
		char c = str.charAt(i);
		if(c == 'x') {
		return i;
		}
	}
	return -1;
}

public Polynomial(double[] coefficients, int[] powers){

	this.coefficients = coefficients.clone();
	this.powers = powers.clone();
}

public Polynomial add(Polynomial p) {
	
	
	int uniquePow = p.powers.length;
	Polynomial result = null;

	for(int i =0; i < this.powers.length; i++){
		boolean found = false;
		for(int j = 0; j < p.powers.length; j++)
		{
			if(this.powers[i] == p.powers[j])
			{
				found = true;
			}

		}
		if(found == false){
			uniquePow++;
		}

	}
	result = new Polynomial(new double[uniquePow], new int[uniquePow]);
	for(int k =0; k< p.powers.length; k++){
		result.coefficients[k] = p.coefficients[k];
		result.powers[k] = p.powers[k];

	}
	int gup = p.powers.length;
	for(int l = 0; l < this.powers.length; l++)
	{ boolean search = false;
		for(int y = 0; y < p.powers.length; y++){
				if(this.powers[l] == p.powers[y])
			{
				//System.out.println("choco gupper");
				result.coefficients[y] += this.coefficients[l];
				search = true;
			}

		}
		if(!search){
			result.coefficients[gup] = this.coefficients[l];
			result.powers[gup] = this.powers[l];
			gup++;
		}
	}
	
	return result;
}

public Polynomial multiply(Polynomial p) {


	HashMap<Integer, Double> tracker = new HashMap<Integer, Double>();
	for(int i = 0; i < this.powers.length; i++) {
		for(int j =0; j < p.powers.length;j++)
		{
			int multied = this.powers[i] + p.powers[j];
			if(tracker.containsKey(multied)) {
				double old = tracker.get(multied);
				tracker.put(multied, old + (this.coefficients[i] * p.coefficients[j]));

			} else{
			tracker.put(multied, this.coefficients[i] * p.coefficients[j] );
			}
		}
	}
	int uniqPow = tracker.size();
	Polynomial result = new Polynomial(new double[uniqPow], new int[uniqPow] );
	int index = 0;
	for(int key : tracker.keySet()) 
	{
		result.coefficients[index] = tracker.get(key);
		result.powers[index] = key;

		index++;
	}


	return result;
}

public void PrintStuff() 
{
	for(int i = 0; i < this.coefficients.length; i ++){

		System.out.println(coefficients[i] +"x^" + powers[i] +" +");
	}
}

public double evaluate(double in) {
	double res = 0;
	for(int i =0; i < coefficients.length; i ++) {
		
		res += Math.pow(in, powers[i]) * coefficients[i];
	}
	 return res;
}
public boolean hasRoot(double in) {
	
	return evaluate(in) == 0;

}

public void saveToFile(String name) {
	String msg = "";
	for(int i = 0; i <powers.length; i ++) {
		int power = powers[i];
		double coefficient = coefficients[i];
			if(coefficient>0 && i >0) {
				msg += "+";
			}
			
			msg += Double.toString(coefficient);
		if(power!=0)
		{
			msg+= "x" + Integer.toString(power);

		}

	}
	//System.out.println(msg);
	try{
	FileWriter writer = new FileWriter(name);
	writer.write(msg);
	writer.close();
	}
	catch(IOException e) {
		System.out.println("Oh no that file path does not exist :( ");
	}
}

}