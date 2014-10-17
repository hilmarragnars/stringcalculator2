package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",") || text.contains("\n") || text.startsWith("//")){
			return sum(splitNumbers(text));
		}
		else{
			return 1;
		}
	} 

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
		String delimeter = "";
		if(numbers.startsWith("//")){
			delimeter = numbers.substring(2,3);
			int index = numbers.indexOf("\n");
			numbers = numbers.substring(index + 1);
		}
		delimeter = "[\n" + "," + delimeter +"]";
		return numbers.split(delimeter);

	}
     
    private static int sum(String[] numbers){
    	int total = 0;
		for(String number : numbers){
			total += toInt(number);
		}
		return total;
    }
}