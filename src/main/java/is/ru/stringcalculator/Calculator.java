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
			return toInt(text);
		}
	} 

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
		String delimeter = null;
		if(numbers.startsWith("//")){
			int index = numbers.indexOf("\n");
			if(isLongOrMultibleDelimeter(numbers))
			{
				delimeter = getDelimeters(numbers);
			}
			else{
				delimeter = numbers.substring(2,3);
			}
			numbers = numbers.substring(index + 1);
		}
		delimeter = "[\n" + "," + delimeter +"]+";
		return numbers.split(delimeter);

	}
	private static String getDelimeters(String numbers)
	{
		String delimeter = "";
		int index = 0;
		int nextIndex = 0;
		do{
			index = numbers.indexOf("[", index + 1);
			nextIndex = numbers.indexOf("]", index);
			if(index != -1){
				delimeter += numbers.substring(index + 1, nextIndex);
			}

		}while(index !=-1);
		return delimeter;
	}
	private static boolean isLongOrMultibleDelimeter(String numbers)
	{
		if(numbers.substring(2).startsWith("[")){
			return true;
		}
		return false;
	}

    private static int sum(String[] numbers){
    	int total = 0;
		for(String number : numbers){
			if(toInt(number) < 0){
				String message = exceptionMessage(numbers);
				throw new IllegalArgumentException(message);
			}
			if(toInt(number) < 1000){
				total += toInt(number);
			}
		}
		return total;
    }
    private static String exceptionMessage(String[] numbers){
    	String messageText = "negatives not allowed: ";
    	for(int i = 0; i < numbers.length; i++){
    		if(numbers[i].contains("-")){
    			messageText = messageText + numbers[i] + ",";
    		}
    	}
    	messageText = messageText.substring(0, messageText.length() - 1);
    	return messageText;
    }
}