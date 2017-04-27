package Assignment1;

public class ArmStrong {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		ArmStrong  a = new ArmStrong();
		 //int number = 405;
		 int number =  371;
		 int n = number;
		 int armStrongNumber = 0;
		 int digit = 0;
		 int nOD = a.numberOfDigits(number);
		 System.out.println("Number of digits " +a.numberOfDigits(number));
		 
        
		//check whether a given number is ArmStrong or not
		for (int i = 0;i<=nOD; i++){
		       digit = number%10;
		      armStrongNumber = armStrongNumber + a.power(digit,nOD);
		      number = number/10;
		}
		if(n == armStrongNumber){
			System.out.println("Given number is ArmStrong");
		}
		else{
			System.out.println("Given number is not ArmStrong");
		}
		
	}
	
		
	//power of a number
	public int power(int base ,int exponant ){
		int power =base;
		for(int j=2;j<=exponant;j++){
		 power = power*base;
		// System.out.println(power);
		// System.out.println(j);
		}
		return power;
		
	}
	
	//to find number of digits in a number
	public  int numberOfDigits(int number){
		int digits = 0;
		while(number!=0){
			int d = number%10;
			number = number / 10;
			digits = digits + 1;
		}
		return digits;
		
	}

}
