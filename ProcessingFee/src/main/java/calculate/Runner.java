package calculate;

import java.io.IOException;

public class Runner {
	
	public static void main(String[] args) {
		
		ProcessFee calculator = new ProcessFee();
		String inputFilePath = "src/main/resources/log.csv";
		String outputFilePath = "src/main/resources/output.csv";
		
		try {
			calculator.readAndParse(inputFilePath);
			calculator.populateBuySell();
			calculator.writeToFile(calculator.sortFee(), outputFilePath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
